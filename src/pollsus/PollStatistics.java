package pollsus;

public class PollStatistics {
    public int printAnswers(String[] answers){
        try {
            for (int first=0; first < answers.length; first=first+1) {
                System.out.print(answers[first]+", ");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return 1;  //성공하면 1, 실패하면 0 
    }
}
