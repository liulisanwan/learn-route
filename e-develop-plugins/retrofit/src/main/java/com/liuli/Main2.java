package com.liuli;

public class Main2 {
    public static void main(String[] args) {
        OkHttpUtil util = new OkHttpUtil();
//        ClientResponseData data = util.executeApi(Test.class, ClientResponseData.class, "postWrite");
//        ClientResponseData data = util.executeApi(Test.class, "postWrite",call -> {
//            try {
//                Response<ClientResponseData> execute = call.execute();
//                if (execute.isSuccessful()) {
//                    return execute.body();
//                }
//            } catch (Exception e) {
//                // 处理异常
//                // log.error(e.getMessage(), e);
//                throw new RuntimeException(e);
//            }
//            return null;
//        } );
        ClientResponseData data = util.executeApi(Test.class, ClientResponseData.class, "postWrite");
        System.err.println(data);
    }
}
