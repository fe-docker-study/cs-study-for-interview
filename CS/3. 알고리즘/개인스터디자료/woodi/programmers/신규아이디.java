class Solution {
    String removeDot(String lid) {
		while(true) {
			if(lid.startsWith(".")) {
				lid = lid.substring(1);
			}
			if(lid.endsWith(".")) {
				lid = lid.substring(0, lid.length()-1);
			} 
			
			if(!lid.startsWith(".") && !lid.endsWith(".")) break;
		}
		
		return lid;
	}
	boolean checkRule(String id) {
		if(id.length()<3 || id.length()>15) return false;
		
		for(int i=0; i<id.length(); i++) {
			if(!((id.charAt(i)>='a' && id.charAt(i)<='z') || id.charAt(i)=='-' 
					|| id.charAt(i)=='_' || id.charAt(i)=='.' || (id.charAt(i)>='0' && id.charAt(i)<='9'))) 
				return false;
		}
		
		if(id.startsWith(".") || id.endsWith(".")) return false;
		
		for(int i=1; i<id.length(); i++) {
			if(id.charAt(i-1)=='.' && id.charAt(i)=='.') return false;
		}
		return true;
	}
	
	String recommendNewId(String id) {
		String lid = id.toLowerCase();
		String new_id = "";
		
		// 앞뒤로 . 없애기
		lid = removeDot(lid);
		
		for(int i=0; i<lid.length(); i++) {
			if((lid.charAt(i)>='a' && lid.charAt(i)<='z') || lid.charAt(i)=='-' 
					|| lid.charAt(i)=='_' || lid.charAt(i)=='.' || (lid.charAt(i)>='0' && lid.charAt(i)<='9')) {
				if(lid.charAt(i)=='.' && new_id.endsWith(".")) {
					continue;
				}
				new_id += lid.charAt(i);
			} 
		}
//		System.out.println(new_id);
		new_id = removeDot(new_id);
		if(new_id.length()==0) return "aaa";
		if(new_id.length()>=16) {
			new_id = new_id.substring(0, 15);
			new_id = removeDot(new_id);
		}
		
		while(new_id.length()<=2) {
			new_id += new_id.charAt(new_id.length()-1);
		}
		
		return new_id;
		
	}
    public String solution(String new_id) {
        if(!checkRule(new_id)) {
        	if(new_id.length()==0) return "aaa";
        	new_id = recommendNewId(new_id);
        }
        return new_id;
    }
}
