package gather;

import test.DateUtil;

public class TestCollection {

    public static void main(String[] args) {
        String hh = DateUtil.getNow("HH");
        System.out.println(hh);

        if (Integer.valueOf(hh) >= 22 || Integer.valueOf(hh) < 3) {
            System.out.println("qingkong");
        }else {
            System.out.println("调价");
        }
   /*     //int[] adq={1,2,3};
        List<Integer> strings=new ArrayList<>();
        strings.add(1);
        strings.add(9);
        strings.add(10);
        strings.add(7);
        strings.add(8);

        Collections.sort(strings);
        System.out.println(strings);
        Set<String> stringc=new HashSet<>();
        stringc.add("zs");
        stringc.add("sad");
        stringc.add("fasf");
        stringc.add("3");
        stringc.add("fa3sf");
        stringc.add("fas22f");
        Iterator it=stringc.iterator();

        while (it.hasNext()){

            if("zs".equals(it.next())){

                it.remove();
            }

        }
        System.out.println(stringc);*/
    }




/* bnif (!CollectionUtils.isEmpty(userFeeRules)) {
        // 根据优惠计算手续费金额
        BigDecimal discount = orderSumBean.getCountryPriceOrderSum().subtract(orderSumBean.getOrderSum());
        BigDecimal fee = new BigDecimal("0");
        for (UserFeeRule rule : userFeeRules) {
            if (discount.compareTo(new BigDecimal(rule.getThreshold())) >= 0) {
                fee = new BigDecimal(rule.getFeeValue());
            } else {
                break;
            }
        }
        // 重新计算订单金额
        BigDecimal afterTakeFeeOrderSum = orderSumBean.getOrderSum().add(fee);
        // 如果小于，就收手续费，否则不收
        if (afterTakeFeeOrderSum.compareTo(orderSumBean.getOriginalOrderSum()) < 0) {
            orderSumBean.setOrderSum(afterTakeFeeOrderSum);
            orderSumBean.setUserFeeFlag(1);
            orderSumBean.setUserFee(fee);
        }
    }*/


}
