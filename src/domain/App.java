package domain;

import java.io.*;
import java.util.*;

public class App {

    public static void main(String[] args) throws IOException {
        App app=new App();
        ArrayList<String> arr=new ArrayList<>();
        ArrayList<String> arr1=new ArrayList<>();
        ArrayList<Long> arrNumber=new ArrayList<>();
        ArrayList<String> arrCode=new ArrayList<>();
        Map<String, String> map=new HashMap<>();
        File fileIn=new File("input.txt");
        File fileOut=new File("output.txt");
        PrintWriter pw=new PrintWriter(fileOut);
        BufferedReader bf=new BufferedReader(new FileReader(fileIn));
        String line;
        ArrayList<String> itog=new ArrayList<>();
        String ns="";
        while((line=bf.readLine())!=null) {
            arr1.add(line);
        }
        bf.close();
        int countNumber=Integer.parseInt(arr1.get(0));
//        int countCode=Integer.parseInt(arr1.get(countNumber+1));
        for(int i=1;i<=countNumber;i++){
            arrNumber.add(Long.parseLong(app.del(arr1.get(i))));
        }
        for(int i=countNumber+2;i<arr1.size();i++){
            arrCode.add(arr1.get(i));
        }
        for(int i=0;i<arrCode.size();i++){
            String str="";
            String str2="";
            int flag=0;
            StringBuffer sb=new StringBuffer(arrCode.get(i));
            for(int l=0;l<sb.length();l++){
                if(sb.charAt(l)=='-'){
                    flag=1;
                    l++;
                }
                if(flag==0){
                    str=str+sb.charAt(l);
                }else{
                    str2=str2+sb.charAt(l);
                }

            }
            map.put(str2,str);
        }
        ArrayList<String> numberCode=new ArrayList<>();
        for(Map.Entry<String, String> item : map.entrySet()){
            numberCode.add(item.getValue());
        }
        for(int i=0;i<arrNumber.size();i++){
            for(int j=0;j<numberCode.size();j++){
                String number=Long.toString(arrNumber.get(i));
                String code=numberCode.get(j);
                int dlinaFirst=app.first(code)-2;
                int dlinaLast=app.last(code);
                code=app.del(code);
                if(number.length()==code.length()){
                    char[] number2=number.toCharArray();
                    char[] code2=code.toCharArray();
                    number="";
                    code="";
                    int m=0;
                    while(m<dlinaLast){
                        number=number+number2[m];
                        code=code+code2[m];
                        m++;
                    }
                    if(number.equals(code)) {
                        String stroka="";
                        for(Map.Entry<String, String> item : map.entrySet()){
                            if(item.getValue().equals(numberCode.get(j))){
                                stroka=arrNumber.get(i)+" - "+item.getKey();
                            }
                        }
                        stroka=app.ended(stroka, dlinaFirst, dlinaLast);
                        stroka="+"+stroka;
                        pw.println(stroka);
                    }
                }

            }
        }
        pw.close();


    }

    public String del(String s){
        String str="";
        StringBuffer sb=new StringBuffer(s);
        for(int i=0;i<sb.length();i++){
            if(sb.charAt(i)>'0' && sb.charAt(i)<='9' || sb.charAt(i)=='X' ){
                if(sb.charAt(i)=='X'){
                    str=str+'0';
                }else{
                    str=str+sb.charAt(i);
                }
            }

        }
        return str;
    }
    public String delN(String s){
        String str="";
        StringBuffer sb=new StringBuffer(s);
        for(int i=0;i<sb.length();i++){
            if(sb.charAt(i)>'0' && sb.charAt(i)<='9' || sb.charAt(i)=='X' ||sb.charAt(i)==')'){
                    str=str+sb.charAt(i);
            }

        }
        return str;
    }

    public int last(String s){
        App app=new App();
        int z=0;
        s=app.delN(s);
        char[] str=s.toCharArray();
        for(int i=0;i<str.length;i++){
            if(str[i]==')'){
                z=i;
            }
        }

            return z;
    }
    public int first(String s){
        App app=new App();
        int z=0;
        char[] str=s.toCharArray();
        for(int i=0;i<str.length;i++){
            if(str[i]=='('){
                z=i;
            }
        }

        return z;
    }
    public String ended(String s,int a,int b){
        char[] str=s.toCharArray();
        String itog="";
        for(int i=0;i<str.length;i++){
            if(i== a)
                itog=itog+'(';
            if(i== b)
                itog=itog+')';
            itog=itog+str[i];
        }

        return itog;
    }
}
