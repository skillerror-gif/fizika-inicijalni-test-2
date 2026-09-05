package rs.fizika.inicijalnitest2

object MixedQuestionBank {

    fun buildTest30(): List<Question> {
        val baseEasy = QuestionBank.easyQuestions().shuffled().take(13)
        val baseHard = QuestionBank.hardQuestions().shuffled().take(13)
        val diagramEasy = easyDiagramQuestions().shuffled().take(2)
        val diagramHard = hardDiagramQuestions().shuffled().take(2)
        return (baseEasy + baseHard + diagramEasy + diagramHard).shuffled()
    }

    private fun easyDiagramQuestions(): List<Question> = listOf(
        Question(
            "Sa dijagrama brzine u funkciji vremena odredi ubrzanje tela.",
            listOf("1 m/s²", "2 m/s²", "4 m/s²", "8 m/s²"),
            1,
            "Brzina raste sa 0 na 6 m/s za 3 s, pa je a = Δv/Δt = 6/3 = 2 m/s².",
            DiagramSpec("line", listOf(0f, 2f, 4f, 6f), listOf("0", "1", "2", "3"), "t (s)", "v (m/s)")
        ),
        Question(
            "Sa dijagrama puta u funkciji vremena odredi srednju brzinu.",
            listOf("2 m/s", "5 m/s", "10 m/s", "15 m/s"),
            1,
            "Za 3 s telo pređe 15 m, pa je v = 15/3 = 5 m/s.",
            DiagramSpec("line", listOf(0f, 5f, 10f, 15f), listOf("0", "1", "2", "3"), "t (s)", "s (m)")
        ),
        Question(
            "Na dijagramu su prikazane dve suprotno usmerene sile. Kolika je rezultanta i njen smer?",
            listOf("6 N udesno", "14 N udesno", "6 N ulevo", "4 N udesno"),
            0,
            "Veća sila je 10 N udesno, a manja 4 N ulevo, pa je rezultanta 6 N udesno.",
            DiagramSpec("bar", listOf(10f, 4f), listOf("udesno", "ulevo"), "", "F (N)")
        ),
        Question(
            "Na dijagramu su prikazane kinetička i potencijalna energija tela. Kolika je ukupna mehanička energija?",
            listOf("60 J", "80 J", "100 J", "120 J"),
            2,
            "Ukupna mehanička energija je zbir: 20 J + 80 J = 100 J.",
            DiagramSpec("bar", listOf(20f, 80f), listOf("Ek", "Ep"), "", "E (J)")
        ),
        Question(
            "Na dijagramu su prikazane vrednosti gravitacionog ubrzanja na Zemlji i Mesecu. Gde je g manje?",
            listOf("na Zemlji", "na Mesecu", "isto je", "ne može se zaključiti"),
            1,
            "Stubac za Mesec je znatno niži: oko 1,6 m/s² u odnosu na oko 9,8 m/s² na Zemlji.",
            DiagramSpec("bar", listOf(9.8f, 1.6f), listOf("Zemlja", "Mesec"), "", "g (m/s²)")
        ),
        Question(
            "Dijagram prikazuje momente dve sile oko ose. Da li je telo u rotacionoj ravnoteži?",
            listOf("da", "ne, okreće se ulevo", "ne, okreće se udesno", "nema dovoljno podataka"),
            0,
            "Momenti su jednaki po intenzitetu i suprotnih smerova, pa je zbir momenata jednak nuli.",
            DiagramSpec("bar", listOf(20f, 20f), listOf("levo", "desno"), "", "M (N·m)")
        )
    )

    private fun hardDiagramQuestions(): List<Question> = listOf(
        Question(
            "Na v–t dijagramu brzina ravnomerno raste od 0 do 12 m/s za 3 s. Koliki je pomeraj za ta 3 s?",
            listOf("12 m", "18 m", "24 m", "36 m"),
            1,
            "Pomeraj je površina ispod v–t grafika: trougao površine 3·12/2 = 18 m.",
            DiagramSpec("line", listOf(0f, 4f, 8f, 12f), listOf("0", "1", "2", "3"), "t (s)", "v (m/s)")
        ),
        Question(
            "Na v–t dijagramu brzina opada sa 12 m/s na 0. Posle koliko vremena se telo zaustavlja?",
            listOf("1 s", "2 s", "3 s", "4 s"),
            2,
            "Grafik seče vremensku osu pri t = 3 s, kada brzina postaje nula.",
            DiagramSpec("line", listOf(12f, 8f, 4f, 0f), listOf("0", "1", "2", "3"), "t (s)", "v (m/s)")
        ),
        Question(
            "Sa dijagrama sile u funkciji vremena odredi najveću vrednost sile.",
            listOf("4 N", "6 N", "8 N", "12 N"),
            2,
            "Najviša tačka dijagrama odgovara sili od 8 N.",
            DiagramSpec("line", listOf(0f, 4f, 8f, 4f, 0f), listOf("0", "1", "2", "3", "4"), "t (s)", "F (N)")
        ),
        Question(
            "Dijagram prikazuje izvršeni rad u zavisnosti od vremena. Kolika je prosečna snaga do t = 3 s?",
            listOf("10 W", "20 W", "30 W", "60 W"),
            1,
            "Za 3 s izvršeno je 60 J rada, pa je P = A/t = 60/3 = 20 W.",
            DiagramSpec("line", listOf(0f, 20f, 40f, 60f), listOf("0", "1", "2", "3"), "t (s)", "A (J)")
        ),
        Question(
            "Na dijagramu su prikazani kraci iste sile. U kom slučaju je moment sile najveći?",
            listOf("r = 0,2 m", "r = 0,4 m", "r = 0,6 m", "isti je u svim slučajevima"),
            2,
            "Za istu silu moment M = Fr raste sa krakom sile, pa je najveći za r = 0,6 m.",
            DiagramSpec("bar", listOf(0.2f, 0.4f, 0.6f), listOf("0,2", "0,4", "0,6"), "r (m)", "")
        ),
        Question(
            "Na dijagramu su prikazani impulsi dva tela pre sudara: +6 kg·m/s i −2 kg·m/s. Koliki je ukupan impuls sistema?",
            listOf("2 kg·m/s", "4 kg·m/s", "6 kg·m/s", "8 kg·m/s"),
            1,
            "Impulsi su suprotnih smerova, pa je ukupan impuls 6 − 2 = 4 kg·m/s.",
            DiagramSpec("bar", listOf(6f, 2f), listOf("+p₁", "−p₂"), "", "|p| (kg·m/s)")
        )
    )
}
