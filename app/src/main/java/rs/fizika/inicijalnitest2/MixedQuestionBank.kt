package rs.fizika.inicijalnitest2

object MixedQuestionBank {

    fun buildTest20(): List<Question> {
        // 14 pitanja iz postojeće baze + 6 računskih zadataka.
        // Računski deo: 3 osnovna + 3 srednja, nasumično iz nove baze od 70 zadataka.
        val theoryEasy = QuestionBank.easyQuestions().shuffled().take(7)
        val theoryHard = QuestionBank.hardQuestions().shuffled().take(7)
        val calculations = CalculationQuestionBank.randomSix()
        return (theoryEasy + theoryHard + calculations).shuffled()
    }
}
