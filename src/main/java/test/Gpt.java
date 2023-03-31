package test;

import com.plexpt.chatgpt.ChatGPT;

import java.lang.reflect.Proxy;

public class Gpt {
    private static ChatGPT chatGPT;
        static {
         chatGPT = ChatGPT.builder()
                .apiKey("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")
                // .proxy(proxy)
                .apiHost("https://api.openai.com/") //反向代理地址
                .build()
                .init();
        }


    public static void main(String[] args) {

        //国内需要代理
        //Proxy proxy = Proxys.http("127.0.0.1", 1080);

//        ChatGPT chatGPT = ChatGPT.builder()
//                .apiKey("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")
//               // .proxy(proxy)
//                .apiHost("https://api.openai.com/") //反向代理地址
//                .build()
//                .init();

        String chat = chat(chatGPT, "海贼王");

        System.out.println(chat);
    }

    public static String chat(ChatGPT chatGPT,String ques){

        String res = chatGPT.chat(ques);

        return  res;
    }
}
