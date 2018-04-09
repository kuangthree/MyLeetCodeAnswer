package solutions;

import java.util.ArrayList;
import java.util.List;
/**
 * 要求：给一段字符串，要求输出在里面添加点符号所生成的所有合法的IP地址。
 * 比如输入2552551111
 * 输出 255.255.1.111
 *      255.255.11.11
 *      255.255.111.1*/
public class FindValidIpAddress {

    public static List<String> restoreIPAddr(String ip){
        List<String> list = new ArrayList<>();
        for(int dot1=0;dot1<ip.length()-2;dot1++){
            for(int dot2=dot1+1;dot2<ip.length()-1;dot2++){
                for(int dot3=dot2+1;dot3<ip.length()-1;dot3++){
                    String a = ip.substring(0,dot1+1);
                    String b = ip.substring(dot1+1,dot2+1);
                    String c = ip.substring(dot2+1,dot3+1);
                    String d = ip.substring(dot3+1,ip.length());
                    String m = a+"."+b+"."+c+"."+d;
                    if(isValidIp(m)){
                        list.add(m);
                    }
                }
            }
        }
        return list;
    }

    public static boolean isValidIp(String ip){
        String[] parts = ip.split("\\.");
        for(String part:parts){
            if(part.length()!=1&&part.startsWith("0"))return false;
            try {
                int i = Integer.parseInt(part);
                if(!(i<=255&&i>=0))return false;
            }catch (NumberFormatException e){
                return false;
            }
        }
        return true;
    }

}
