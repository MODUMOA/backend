package com.saessak.momo.payment.controller;

import com.saessak.momo.global.dto.ResponseDto;
import com.saessak.momo.payment.dto.PaymentDetailItem;
import com.saessak.momo.payment.dto.PaymentItem;
import com.saessak.momo.payment.dto.TotalPriceItem;
import com.saessak.momo.payment.model.service.PaymentService;
import com.saessak.momo.trash.dto.TrashHistoryItem;
import com.saessak.momo.user.dto.UserExpItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";
    private final PaymentService paymentService;

    @PostMapping()
    public ResponseEntity<ResponseDto> updateStatus() throws Exception{

        try{
            // 1. 오늘 쓰레기 버린 전체 userIdx 리스트 받아오기
            List<Integer> users = paymentService.getTodayUsers();


            // 2. userIdx 별로 payment 테이블에 insert 하기
            for(int user : users){
                paymentService.createPayment(user);
            }

            // 3. trash_history 테이블에서 오늘 날짜 데이터들 가져오기
            List<TrashHistoryItem> trashHistoryItemList = paymentService.getTrashHistory();

            for(TrashHistoryItem item : trashHistoryItemList){

                // 4. payment 테이블에서 paymentIdx 받아오기
                int paymentIdx = paymentService.getPaymentIdx(item.getUserIdx());

                // 5. payment_detail 테이블에 insert 하기
                PaymentDetailItem paymentDetailItem = new PaymentDetailItem();

                int trashIdx = item.getTrashIdx();
                int trashWeight = item.getTrashWeight();

                // 5 - 1) 쓰레기 별 단위 무게당 가격 받아오기
                double price = paymentService.getTrashPrice(trashIdx);

                // 5 - 2) PaymentDetailItem 세팅
                paymentDetailItem.setPaymentIdx(paymentIdx);
                paymentDetailItem.setTrashIdx(trashIdx);
                paymentDetailItem.setTrashWeight(trashWeight);
                paymentDetailItem.setPrice((double)trashWeight * price);

                // 5 - 3) insert
                paymentService.createPaymentDetail(paymentDetailItem);

                // 6. payment 테이블 total_price UPDATE
                TotalPriceItem totalPriceItem = new TotalPriceItem();
                totalPriceItem.setPaymentIdx(paymentIdx);
                totalPriceItem.setPrice((double)trashWeight * price);

                paymentService.updateTotalPrice(totalPriceItem);

                // ----- payment 끝 ----- //

                // 1. user_idx와 trash_idx 일치하는 trash_history 받아오기 (최대 2개)
                List<TrashHistoryItem> list = paymentService.getTrashHistoryByIdx(item.getUserIdx(), trashIdx);

                // 2. 평균 배출량 설정
                int standard = 0;   // 평균 배출량
                double mul = 0;

                if(trashIdx == 1){
                    standard = 318;
                    mul = 1.08;
                }
                else if(trashIdx == 2){
                    standard = 249;
                    mul = 1.08;
                }
                else if(trashIdx == 3){
                    standard = 283;
                    mul = 2.75;
                }

                // parameter 설정
                UserExpItem userExpItem = new UserExpItem();
                userExpItem.setUserIdx(item.getUserIdx());

                // 3. list 개수 1개인 경우 바로 user 테이블 update
                if(list.size() == 1){
                    TrashHistoryItem today = list.get(0);

                    // 3 - 1) exp 계산
                    int exp = (int)((standard - today.getTrashWeight()) * mul);
                    userExpItem.setExp(exp);

                    paymentService.updateUserExp(userExpItem);
                }
                // 4. list 개수 2개인 경우
                else if(list.size() == 2){
                    // 4 - 1) 두 날짜 비교
                    int diff = paymentService.getDateDiff(list.get(0).getDate(), list.get(1).getDate());

                    // 날짜 차이만큼 반복해서 user 테이블 update
                    for(int i=0; i<diff; i++){
                        // 하루에 들어갈 양은 trashWeight / diff
                        int exp = (int)((standard - (list.get(0).getTrashWeight() / diff)) * mul);
                        userExpItem.setExp(exp);

                        paymentService.updateUserExp(userExpItem);
                    }
                }

                // 5. userExp UPDATE 후 10000 넘으면
                int exp = paymentService.getUserExp(item.getUserIdx());

                if(exp >= 10000){
                    // 5 - 1) exp 업데이트 하기(exp 10000 빼기, level + 1)
                    paymentService.updateExpMinus(item.getUserIdx());

                    // 5 - 2) tree collection update하기 (일단 1번 나무)
                    paymentService.updateTreeCollection(item.getUserIdx());
                }


            }

        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto(HttpStatus.NO_CONTENT.value(), FAIL, null));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.value(), SUCCESS, null));

    }

    @GetMapping("/{userIdx}")
    public ResponseEntity<ResponseDto> getPaymentList(@PathVariable("userIdx") String userIdx) throws Exception{
        List<PaymentItem> list = null;

        try{
            // userIdx로 payment 받아오기
            list = paymentService.getPaymentList(Integer.parseInt(userIdx));

            for(PaymentItem item : list){
                // 요일 받아오기
                int weekday = paymentService.getWeekDay(item.getDate());
                String weekdayString = "";

                if(weekday == 0){
                    weekdayString = "월";
                } else if(weekday == 1){
                    weekdayString = "화";
                } else if(weekday == 2){
                    weekdayString = "수";
                } else if(weekday == 3){
                    weekdayString = "목";
                } else if(weekday == 4){
                    weekdayString = "금";
                } else if(weekday == 5){
                    weekdayString = "토";
                } else if(weekday == 6){
                    weekdayString = "일";
                }

                item.setWeekday(weekdayString);

                // details 받아오기
                List<PaymentDetailItem> detailList = paymentService.getPaymentDetail(item.getPaymentIdx());

                // 쓰레기 카테고리 구하기
                for(PaymentDetailItem detailItem : detailList){
                    if(detailItem.getTrashIdx() == 1){
                        detailItem.setCategory("일반쓰레기");
                    }
                    else if(detailItem.getTrashIdx() == 2){
                        detailItem.setCategory("음식물쓰레기");
                    }
                    else if(detailItem.getTrashIdx() == 3){
                        detailItem.setCategory("플라스틱");
                    }
                    else if(detailItem.getTrashIdx() == 4){
                        detailItem.setCategory("캔");
                    }
                }

                item.setDetails(detailList);
            }

        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto(HttpStatus.NO_CONTENT.value(), FAIL, null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.value(), SUCCESS, list));

    }


}
