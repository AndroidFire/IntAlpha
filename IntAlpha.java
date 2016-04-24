import java.util.regex.Pattern;

public class IntAlpha {
    private static String[] ENGLISH_LETTER = {" ","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r"
            ,"s","t","u","v","w","x","y","z"};

    public IntAlpha() {

    }


    public String decrypt(String cipher,String key) {
        int en_key = convertKey(key);
        String[] per_text = cipher.split(Pattern.quote("-"));
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : per_text) {
            int i = Integer.parseInt(s);
            stringBuilder.append(i/en_key).append("-");
        }
        String half_en = stringBuilder.toString();
        if (half_en.endsWith("-")) {

            half_en = half_en.substring(0,half_en.length()-1);
        }


        String[] per = half_en.split(Pattern.quote("-"));
        StringBuilder fi = new StringBuilder();
        for (String value : per) {

            int k = Integer.parseInt(value);
            for (int j = 0; j < ENGLISH_LETTER.length; j++) {
                if (k == j) {
                    fi.append(ENGLISH_LETTER[j]);
                }
            }
        }
        return fi.toString();
    }
    public String encrypt(String plain_text,String key) {
        plain_text = plain_text.toLowerCase();
        String half_en = convertToNo(plain_text);
        int en_key = convertKey(key);
        String[] per_text = half_en.split(Pattern.quote("-"));
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : per_text) {
            int i = Integer.parseInt(string);
            i = i * en_key;
            stringBuilder.append(i).append("-");
        }
        String return_Value =  stringBuilder.toString();
        if (return_Value.endsWith("-")) {

            return_Value = return_Value.substring(0,return_Value.length()-1);
        }
        return return_Value;
    }

    private  int convertKey(String key) {
        String key_y = convertToNo(key);

        String[] per_text = key_y.split(Pattern.quote("-"));
        int no = 1;
        for (String string : per_text) {
            int i = Integer.parseInt(string);
            no = no*i;
        }
        return no;
    }

    private String convertToNo(String plain_text) {
        StringBuilder stringBuilder = new StringBuilder();
        plain_text = plain_text.toLowerCase();
        String[] per_text = plain_text.split("");
        for (String letter : per_text) {
            for (int j = 0; j < ENGLISH_LETTER.length; j++) {
                String alpha = ENGLISH_LETTER[j];
                if (letter.equals(alpha)) {

                    stringBuilder.append(j).append("-");

                    break;
                }
            }
        }
        String return_Value =  stringBuilder.toString();
        if (return_Value.endsWith("-")) {

            return_Value = return_Value.substring(0,return_Value.length()-1);
        }
        return return_Value;
    }
}
