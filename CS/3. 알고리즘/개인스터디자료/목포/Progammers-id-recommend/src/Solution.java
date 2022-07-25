import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    Pattern pattern = Pattern.compile("[^a-z0-9.\\w\\w-]");
    Matcher matcher;
    public String solution(String id) {
        String answer = "";
        matcher = pattern.matcher(id);

        if (!validation(id)) {
            answer = recommend(id);
        }else {
            answer = id;
        }
        return answer;
    }

    boolean validation(String id) {
        if (!validationLength(id) || !validationChar(id) || !validationDotPosition(id) || !validationDotSequence(id)) {
            return false;
        }

        return true;
    }

    String recommend(String id){

        // 1. Upper -> Lower
        id = id.toLowerCase();

        // 2. 조건 문자 이외의 문자 제거
        matcher = pattern.matcher(id);
        id = matcher.replaceAll("");

        // 3. 마침표 위치 변경
        while (id.length() > 0 && (!validationDotSequence(id) || !validationDotPosition(id))) {
            id = changeDot(id);
        }


        // 4. 빈 문자열이면 a 추가
        if (id.isEmpty()) id = "a";

        // 5. 16자 이상이면 앞 15개의 문자만 남겨두고, 맨 마지막 문자가 .로 끝나면 제거한다.
        if (id.length() >= 16) {
            id = id.substring(0, 15);
            if (id.charAt(id.length() - 1) == '.') id = id.substring(0, id.length() - 1);
        }


        // 6. id 길이가 2 이하라면 3이 될 때까지 id의 마지막 문자를 반복해서 끝에 붙인다.
        while (id.length() <= 2) {
            id = id + id.charAt(id.length() - 1);
        }

        return id;
    }



    boolean validationLength(String id) {
        if (id.length() < 3 || id.length() >= 16) {
            //System.out.println("caused length");
            return false;
        }
        return true;
    }

    boolean validationChar(String id) {
        if (matcher.find()) {
            //System.out.println("caused character condition");
            return false;
        }
        return true;
    }

    boolean validationDotSequence(String id) {
        if (id.contains("..")) {
            //System.out.println("caused dot sequence");
            return false;
        }
        return true;
    }

    boolean validationDotPosition(String id) {
        if (id.charAt(0) == '.' || id.charAt(id.length()-1) == '.') {
            //System.out.println("caused . position");
            return false;
        }
        return true;
    }

    String changeDot(String id) {
        id = id.replace("..", ".");

        if (id.charAt(0) == '.') {
            id = id.substring(1);
        } else if(id.charAt(id.length() - 1) == '.') {
            id = id.substring(0, id.length() - 1);
        }

        return id;
    }
}
