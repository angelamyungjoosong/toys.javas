import pollsus.PollInitialArrays;
import pollsus.PollScanners;
import pollsus.PollStatistics;

public class PollsWithMethod_us {
    public static void main(String[] args) {
        try {
            String[] answers = {"2","1"}; //parameters 변수를 가상으로 두고 돌려보고 스캐너사용. 
            String[][] polls; //parameters

            PollInitialArrays pollinitialarrays =new PollInitialArrays();
            polls = pollinitialarrays.init(); //설문 내용 초기화

            PollScanners pollScanners = new PollScanners();
            answers = pollScanners.pollWithAnswers(polls);
        

           PollStatistics pollstatistics= new PollStatistics();
           pollstatistics.printAnswers(answers);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    // return 0;
}