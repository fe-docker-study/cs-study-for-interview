import java.util.Stack;

class Solution {

    public static String expand(String str, int low, int high)
    {
        // expand in both directions
        while (low >= 0 && high < str.length() &&
                (str.charAt(low) == str.charAt(high))) {
            low--;
            high++;
        }

        // return palindromic substring
        return str.substring(low + 1, high);
    }

    public static String longestPalindrome(String str)
    {
        if (str == null || str.length() == 0) {
            return str;
        }


        String max_str = "", curr_str;

        int max_length = 0, curr_length;

        for (int i = 0; i < str.length(); i++)
        {

            curr_str = expand(str, i, i);
            curr_length = curr_str.length();

            if (curr_length > max_length)
            {
                max_length = curr_length;
                max_str = curr_str;
            }

            curr_str = expand(str, i, i + 1);
            curr_length = curr_str.length();

            if (curr_length > max_length)
            {
                max_length = curr_length;
                max_str = curr_str;
            }
        }

        return max_str;
    }
}