package editor;

public class TextEditor {
    public static void main(String [] args){
        String [][]queries = new String[][]{{"APPEND","You'll never find a rainbow if you're looking down"}, 
 {"SELECT","6","50"}, 
 {"BACKSPACE"}, 
 {"SELECT","0","6"}, 
 {"COPY"}, 
 {"PASTE"}, 
 {"PASTE"}, 
 {"PASTE"}, 
 {"PASTE"}, 
 {"PASTE"}, 
 {"PASTE"}, 
 {"PASTE"}, 
 {"PASTE"}, 
 {"PASTE"}, 
 {"PASTE"}, 
 {"BACKSPACE"}};

        solution(queries);

    }
    static String[] solution(String[][] queries) {
        StringBuilder stringBuilder = new StringBuilder();
        int size = queries.length;
        String[] ans = new String[size];
        int index=0;
        int cursorP=0;
        int selectS=-1;
        int selectE=-1;
        String copiedText = "";
        for(String[] s : queries){
            if(s[0].equals("APPEND")){
                if(selectS!=-1 && selectE!=-1){
                    stringBuilder.replace(selectS,selectE,s[1]);
                    ans[index] = stringBuilder.toString();
                    cursorP = selectS + s[1].length();
                    index++;
                    selectS=selectE=-1;
                }else {
                    stringBuilder.insert(cursorP, s[1]);
                    ans[index] = stringBuilder.toString();
                    cursorP = cursorP + s[1].length();
                    index++;
                }
            }
            else if(s[0].equals("MOVE")){
                ans[index]=stringBuilder.toString();
                cursorP=Integer.parseInt(s[1]);
                if(cursorP<0){
                    cursorP=0;
                }else if(cursorP>ans[index].length()){
                    cursorP=ans[index].length();
                }
                index++;
            }
            else if(s[0].equals("BACKSPACE")){
                if(selectS!=-1 && selectE!=-1){
                    stringBuilder.replace(selectS,selectE,"");
                    ans[index] = stringBuilder.toString();
                    cursorP = selectS;
                    index++;
                    selectS=selectE=-1;
                }else {
                    if (cursorP > 0) {
                        cursorP--;
                        stringBuilder.deleteCharAt(cursorP);
                        ans[index] = stringBuilder.toString();
                        index++;
                    } else {
                        ans[index] = stringBuilder.toString();
                        cursorP = 0;
                        index++;
                    }
                }
            }
            else if(s[0].equals("SELECT")){
                selectS = Integer.parseInt(s[1]);
                selectE = Integer.parseInt(s[2]);
                ans[index] = stringBuilder.toString();
                index++;
            }
            else if(s[0].equals("COPY")){
                if(selectS!=-1 && selectE!=-1){
                    copiedText = stringBuilder.substring(selectS,selectE);
                }
                ans[index] = stringBuilder.toString();
                index++;
            }
            else if(s[0].equals("PASTE")){
                if(selectS!=-1 && selectE!=-1) {
                    stringBuilder.replace(selectS, selectE, copiedText);
                }else{
                    stringBuilder.insert(cursorP, copiedText);
                }
                ans[index] = stringBuilder.toString();
                index++;
            }
        }
        return ans;
    }

}
