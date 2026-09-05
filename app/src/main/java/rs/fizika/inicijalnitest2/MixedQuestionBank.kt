package rs.fizika.inicijalnitest2

object MixedQuestionBank {

    fun buildTest20(): List<Question> {
        val easy = QuestionBank.easyQuestions().shuffled().take(10)
        val hard = QuestionBank.hardQuestions().shuffled().take(10)
        return (easy + hard).shuffled()
    }
}
