package rs.fizika.inicijalnitest2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

data class Question(
    val text: String,
    val options: List<String>,
    val correct: Int,
    val explanation: String
)

class MainActivity : AppCompatActivity() {

    private lateinit var subtitle: TextView
    private lateinit var easyButton: Button
    private lateinit var hardButton: Button
    private lateinit var progress: TextView
    private lateinit var questionText: TextView
    private lateinit var optionsGroup: RadioGroup
    private lateinit var optionButtons: List<RadioButton>
    private lateinit var explanationText: TextView
    private lateinit var nextButton: Button
    private lateinit var resultText: TextView
    private lateinit var restartButton: Button

    private var questions: List<Question> = emptyList()
    private var currentQuestion = 0
    private var score = 0
    private var selectedLevel = ""
    private var answerChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)
        }

        val title = TextView(this).apply {
            text = "Inicijalni test iz fizike\n2. razred gimnazije"
            textSize = 24f
            textAlignment = View.TEXT_ALIGNMENT_CENTER
        }

        subtitle = TextView(this).apply {
            text = "Izaberi nivo"
            textSize = 18f
            textAlignment = View.TEXT_ALIGNMENT_CENTER
            setPadding(0, 24, 0, 24)
        }

        easyButton = Button(this).apply {
            text = "Lakši nivo"
        }

        hardButton = Button(this).apply {
            text = "Teži nivo"
        }

        progress = TextView(this).apply {
            textSize = 16f
            visibility = View.GONE
            setPadding(0, 20, 0, 10)
        }

        questionText = TextView(this).apply {
            textSize = 20f
            visibility = View.GONE
            setPadding(0, 10, 0, 20)
        }

        optionsGroup = RadioGroup(this).apply {
            visibility = View.GONE
        }

        optionButtons = List(4) {
            RadioButton(this).apply {
                textSize = 17f
                optionsGroup.addView(this)

                setOnClickListener {
                    checkAnswer()
                }
            }
        }

        explanationText = TextView(this).apply {
            textSize = 17f
            visibility = View.GONE
            setPadding(0, 20, 0, 20)
        }

        nextButton = Button(this).apply {
            text = "Sledeće pitanje"
            visibility = View.GONE
        }

        resultText = TextView(this).apply {
            textSize = 22f
            textAlignment = View.TEXT_ALIGNMENT_CENTER
            visibility = View.GONE
            setPadding(0, 30, 0, 20)
        }

        restartButton = Button(this).apply {
            text = "Ponovi test"
            visibility = View.GONE
        }

        root.addView(title)
        root.addView(subtitle)
        root.addView(easyButton)
        root.addView(hardButton)
        root.addView(progress)
        root.addView(questionText)
        root.addView(optionsGroup)
        root.addView(explanationText)
        root.addView(nextButton)
        root.addView(resultText)
        root.addView(restartButton)

        val scrollView = ScrollView(this)
        scrollView.addView(root)

        setContentView(scrollView)

        easyButton.setOnClickListener {
            startTest(easyQuestions(), "Lakši nivo")
        }

        hardButton.setOnClickListener {
            startTest(hardQuestions(), "Teži nivo")
        }

        nextButton.setOnClickListener {
            goToNextQuestion()
        }

        restartButton.setOnClickListener {
            showStartScreen()
        }
    }

    private fun startTest(
        questionList: List<Question>,
        level: String
    ) {
        questions = questionList
        selectedLevel = level
        currentQuestion = 0
        score = 0

        subtitle.text = level

        easyButton.visibility = View.GONE
        hardButton.visibility = View.GONE
        resultText.visibility = View.GONE
        restartButton.visibility = View.GONE

        progress.visibility = View.VISIBLE
        questionText.visibility = View.VISIBLE
        optionsGroup.visibility = View.VISIBLE

        showQuestion()
    }

    private fun showQuestion() {
        val question = questions[currentQuestion]

        answerChecked = false

        progress.text =
            "Pitanje ${currentQuestion + 1} / ${questions.size}"

        questionText.text = question.text

        optionsGroup.clearCheck()

        optionButtons.forEachIndexed { index, button ->
            button.text = question.options[index]
            button.isEnabled = true
        }

        explanationText.text = ""
        explanationText.visibility = View.GONE

        nextButton.visibility = View.GONE
    }

    private fun checkAnswer() {
        if (answerChecked) return

        val selectedId = optionsGroup.checkedRadioButtonId

        if (selectedId == -1) return

        val selectedAnswer =
            optionButtons.indexOfFirst {
                it.id == selectedId
            }

        val question = questions[currentQuestion]

        val isCorrect =
            selectedAnswer == question.correct

        if (isCorrect) {
            score++
        }

        answerChecked = true

        optionButtons.forEach {
            it.isEnabled = false
        }

        if (isCorrect) {
            explanationText.text =
                "✓ Tačan odgovor!\n\n${question.explanation}"
        } else {
            explanationText.text =
                "✗ Netačan odgovor.\n\n" +
                "Tačan odgovor: " +
                question.options[question.correct] +
                "\n\n" +
                question.explanation
        }

        explanationText.visibility = View.VISIBLE

        nextButton.text =
            if (currentQuestion == questions.lastIndex) {
                "Prikaži rezultat"
            } else {
                "Sledeće pitanje"
            }

        nextButton.visibility = View.VISIBLE
    }

    private fun goToNextQuestion() {
        if (!answerChecked) return

        if (currentQuestion < questions.lastIndex) {
            currentQuestion++
            showQuestion()
        } else {
            showResult()
        }
    }

    private fun showResult() {
        progress.visibility = View.GONE
        questionText.visibility = View.GONE
        optionsGroup.visibility = View.GONE
        explanationText.visibility = View.GONE
        nextButton.visibility = View.GONE

        val percent =
            score * 100 / questions.size

        resultText.text =
            "$selectedLevel\n\n" +
            "Tačno: $score / ${questions.size}\n" +
            "Uspešnost: $percent%"

        resultText.visibility = View.VISIBLE
        restartButton.visibility = View.VISIBLE
    }

    private fun showStartScreen() {
        subtitle.text = "Izaberi nivo"

        easyButton.visibility = View.VISIBLE
        hardButton.visibility = View.VISIBLE

        progress.visibility = View.GONE
        questionText.visibility = View.GONE
        optionsGroup.visibility = View.GONE
        explanationText.visibility = View.GONE
        nextButton.visibility = View.GONE
        resultText.visibility = View.GONE
        restartButton.visibility = View.GONE
    }

    private fun easyQuestions(): List<Question> {
        return listOf(

            Question(
                "Telo pređe 120 m za 20 s. Kolika je srednja brzina?",
                listOf(
                    "4 m/s",
                    "6 m/s",
                    "8 m/s",
                    "10 m/s"
                ),
                1,
                "Srednja brzina je v = s/t = 120 m / 20 s = 6 m/s."
            ),

            Question(
                "Koja je SI jedinica za ubrzanje?",
                listOf(
                    "m/s",
                    "m/s²",
                    "N",
                    "J"
                ),
                1,
                "Ubrzanje predstavlja promenu brzine u jedinici vremena, pa je njegova SI jedinica m/s²."
            ),

            Question(
                "Ako se brzina tela ne menja, ubrzanje je:",
                listOf(
                    "pozitivno",
                    "negativno",
                    "jednako nuli",
                    "uvek 9,81 m/s²"
                ),
                2,
                "Ako se vektor brzine ne menja, nema promene brzine, pa je ubrzanje jednako nuli."
            ),

            Question(
                "Drugi Njutnov zakon zapisuje se kao:",
                listOf(
                    "F = ma",
                    "p = mv",
                    "A = Fs",
                    "P = A/t"
                ),
                0,
                "Drugi Njutnov zakon glasi F = ma. Rezultujuća sila jednaka je proizvodu mase i ubrzanja."
            ),

            Question(
                "Na telo mase 2 kg deluje sila 10 N. Koliko je ubrzanje?",
                listOf(
                    "2 m/s²",
                    "5 m/s²",
                    "10 m/s²",
                    "20 m/s²"
                ),
                1,
                "Iz F = ma sledi a = F/m = 10 N / 2 kg = 5 m/s²."
            ),

            Question(
                "Ako je rezultanta svih sila jednaka nuli, telo:",
                listOf(
                    "mora da miruje",
                    "mora da ubrzava",
                    "miruje ili se kreće ravnomerno pravolinijski",
                    "mora da se kreće kružno"
                ),
                2,
                "Ako je rezultanta sila nula, ubrzanje je nula. Telo može mirovati ili se kretati ravnomerno pravolinijski."
            ),

            Question(
                "Koja od navedenih veličina je vektorska?",
                listOf(
                    "masa",
                    "vreme",
                    "put",
                    "brzina"
                ),
                3,
                "Brzina je vektorska veličina jer, osim intenziteta, ima pravac i smer."
            ),

            Question(
                "Rad sile u pravcu pomeranja dat je izrazom:",
                listOf(
                    "A = F/s",
                    "A = Fs",
                    "A = mv",
                    "A = Pt²"
                ),
                1,
                "Kada sila deluje u pravcu pomeranja, rad je A = Fs."
            ),

            Question(
                "SI jedinica za rad i energiju je:",
                listOf(
                    "vat",
                    "džul",
                    "njutn",
                    "paskal"
                ),
                1,
                "SI jedinica za rad i energiju je džul, oznaka J."
            ),

            Question(
                "Kinetička energija tela mase m i brzine v je:",
                listOf(
                    "mv",
                    "mv²",
                    "mv²/2",
                    "mgh"
                ),
                2,
                "Kinetička energija tela izračunava se pomoću izraza Ek = mv²/2."
            ),

            Question(
                "Gravitaciona potencijalna energija blizu Zemljine površine je:",
                listOf(
                    "mgh",
                    "mv²/2",
                    "Fs",
                    "mg/v"
                ),
                0,
                "U blizini Zemljine površine gravitaciona potencijalna energija je Ep = mgh."
            ),

            Question(
                "Ako se visina tela udvostruči, a masa ostane ista, potencijalna energija:",
                listOf(
                    "se prepolovi",
                    "ostaje ista",
                    "udvostruči se",
                    "učetvorostruči se"
                ),
                2,
                "Pošto je Ep = mgh, pri nepromenjenim m i g potencijalna energija je proporcionalna visini."
            ),

            Question(
                "Impuls tela dat je izrazom:",
                listOf(
                    "p = mv",
                    "p = m/v",
                    "p = Ft²",
                    "p = mv²/2"
                ),
                0,
                "Impuls tela jednak je proizvodu mase i brzine: p = mv."
            ),

            Question(
                "Zakon održanja impulsa važi za:",
                listOf(
                    "izolovan sistem",
                    "svako pojedinačno telo",
                    "samo telo koje miruje",
                    "samo kružno kretanje"
                ),
                0,
                "Ukupan impuls izolovanog sistema ostaje konstantan kada je rezultanta spoljašnjih sila jednaka nuli."
            ),

            Question(
                "Moment sile jednak je proizvodu sile i:",
                listOf(
                    "mase",
                    "kraka sile",
                    "brzine",
                    "vremena"
                ),
                1,
                "Intenzitet momenta sile je M = Fr, gde je r krak sile."
            ),

            Question(
                "Uslov translacione ravnoteže tela je:",
                listOf(
                    "ΣF = 0",
                    "ΣF = ma",
                    "ΣM nije jednako 0",
                    "v = 0 obavezno"
                ),
                0,
                "Za translacionu ravnotežu vektorski zbir svih sila koje deluju na telo mora biti jednak nuli."
            ),

            Question(
                "Težina tela mase 2 kg približno je, za g ≈ 10 m/s²:",
                listOf(
                    "5 N",
                    "10 N",
                    "20 N",
                    "200 N"
                ),
                2,
                "Pri ovom približenju intenzitet sile teže je mg = 2 kg · 10 m/s² = 20 N."
            ),

            Question(
                "Slobodan pad bez otpora vazduha je kretanje sa:",
                listOf(
                    "stalnom brzinom",
                    "stalnim ubrzanjem g",
                    "ubrzanjem jednakim nuli",
                    "promenljivom masom"
                ),
                1,
                "Kod slobodnog pada bez otpora vazduha telo se kreće sa približno konstantnim ubrzanjem g."
            ),

            Question(
                "Pri ravnomernom pravolinijskom kretanju grafik s(t) je:",
                listOf(
                    "prava linija",
                    "parabola",
                    "kružnica",
                    "sinusoida"
                ),
                0,
                "Kod ravnomernog pravolinijskog kretanja s = s₀ + vt, pa je grafik s(t) prava linija."
            ),

            Question(
                "Snaga je jednaka:",
                listOf(
                    "A/t",
                    "At",
                    "F/t",
                    "E·t"
                ),
                0,
                "Snaga pokazuje koliko se rada izvrši u jedinici vremena: P = A/t."
            )
        )
    }

    private fun hardQuestions(): List<Question> {
        return listOf(

            Question(
                "Automobil kreće iz mirovanja ubrzanjem 2 m/s². Kolika mu je brzina posle 5 s?",
                listOf(
                    "2 m/s",
                    "5 m/s",
                    "10 m/s",
                    "25 m/s"
                ),
                2,
                "Za ravnomerno ubrzano kretanje iz mirovanja važi v = at = 2 · 5 = 10 m/s."
            ),

            Question(
                "Telo se kreće brzinom 20 m/s i ravnomerno usporava ubrzanjem -4 m/s². Za koliko vremena staje?",
                listOf(
                    "4 s",
                    "5 s",
                    "8 s",
                    "10 s"
                ),
                1,
                "Iz v = v₀ + at dobijamo 0 = 20 - 4t, pa je vreme zaustavljanja t = 5 s."
            ),

            Question(
                "Površina ispod v-t grafika predstavlja:",
                listOf(
                    "ubrzanje",
                    "pomeraj",
                    "snagu",
                    "silu"
                ),
                1,
                "Površina ispod grafika brzine u funkciji vremena predstavlja pomeraj tela."
            ),

            Question(
                "Nagib v-t grafika predstavlja:",
                listOf(
                    "ubrzanje",
                    "put",
                    "impuls",
                    "rad"
                ),
                0,
                "Nagib v-t grafika je promena brzine podeljena vremenom, odnosno ubrzanje."
            ),

            Question(
                "Na telo mase 4 kg deluju sile 14 N udesno i 6 N ulevo. Ubrzanje je:",
                listOf(
                    "2 m/s² udesno",
                    "5 m/s² udesno",
                    "2 m/s² ulevo",
                    "8 m/s² udesno"
                ),
                0,
                "Rezultujuća sila je 14 N - 6 N = 8 N udesno. Zato je a = F/m = 8/4 = 2 m/s² udesno."
            ),

            Question(
                "Telo mase 5 kg klizi po horizontalnoj podlozi. Koeficijent trenja je 0,2, a g ≈ 10 m/s². Sila trenja je:",
                listOf(
                    "1 N",
                    "5 N",
                    "10 N",
                    "25 N"
                ),
                2,
                "Na horizontalnoj podlozi N = mg, pa je Ftr = μN = 0,2 · 5 · 10 = 10 N."
            ),

            Question(
                "Ako sila od 20 N pomeri telo 3 m u svom pravcu, izvršeni rad je:",
                listOf(
                    "6 J",
                    "17 J",
                    "23 J",
                    "60 J"
                ),
                3,
                "Pošto sila deluje u pravcu pomeranja, A = Fs = 20 · 3 = 60 J."
            ),

            Question(
                "Telo mase 2 kg ima brzinu 6 m/s. Kolika je kinetička energija?",
                listOf(
                    "12 J",
                    "18 J",
                    "36 J",
                    "72 J"
                ),
                2,
                "Ek = mv²/2 = 2 · 6² / 2 = 36 J."
            ),

            Question(
                "Telo mase 3 kg podigne se za 4 m. Za g ≈ 10 m/s² porast potencijalne energije je:",
                listOf(
                    "12 J",
                    "30 J",
                    "40 J",
                    "120 J"
                ),
                3,
                "Promena potencijalne energije je ΔEp = mgh = 3 · 10 · 4 = 120 J."
            ),

            Question(
                "Telo pada bez otpora vazduha sa visine 20 m. Za g ≈ 10 m/s² brzina neposredno pre udara u tlo je približno:",
                listOf(
                    "10 m/s",
                    "14 m/s",
                    "20 m/s",
                    "40 m/s"
                ),
                2,
                "Iz v² = 2gh dobijamo v = √(2 · 10 · 20) = √400 = 20 m/s."
            ),

            Question(
                "Telo mase 2 kg, brzine 3 m/s, sudari se i zalepi za telo mase 1 kg koje miruje. Zajednička brzina je:",
                listOf(
                    "1 m/s",
                    "2 m/s",
                    "3 m/s",
                    "6 m/s"
                ),
                1,
                "Iz održanja impulsa: 2 · 3 = (2 + 1)v. Zato je zajednička brzina v = 2 m/s."
            ),

            Question(
                "Na polugu deluje sila 50 N na kraku 0,4 m. Moment sile je:",
                listOf(
                    "20 N·m",
                    "50 N·m",
                    "125 N·m",
                    "0,02 N·m"
                ),
                0,
                "Moment sile je M = Fr = 50 · 0,4 = 20 N·m."
            ),

            Question(
                "Za potpunu ravnotežu krutog tela potrebno je:",
                listOf(
                    "samo ΣF = 0",
                    "samo ΣM = 0",
                    "ΣF = 0 i ΣM = 0",
                    "v = 0 i a nije 0"
                ),
                2,
                "Za ravnotežu krutog tela potrebni su i translaciona ravnoteža ΣF = 0 i rotaciona ravnoteža ΣM = 0."
            ),

            Question(
                "Satelit kruži oko Zemlje. Centripetalnu silu obezbeđuje:",
                listOf(
                    "trenje",
                    "gravitaciona sila",
                    "elastična sila",
                    "Arhimedova sila"
                ),
                1,
                "Gravitaciona sila Zemlje ima ulogu centripetalne sile koja održava satelit na orbiti."
            ),

            Question(
                "Ako se rastojanje između dve mase udvostruči, gravitaciona sila se:",
                listOf(
                    "udvostruči",
                    "prepolovi",
                    "smanji četiri puta",
                    "poveća četiri puta"
                ),
                2,
                "Gravitaciona sila je obrnuto proporcionalna kvadratu rastojanja. Ako se rastojanje udvostruči, sila postaje četiri puta manja."
            ),

            Question(
                "Telo se kreće po kružnici stalnom brzinom po intenzitetu. Ono ipak ima ubrzanje zato što se menja:",
                listOf(
                    "masa",
                    "pravac vektora brzine",
                    "vreme",
                    "energija mirovanja"
                ),
                1,
                "Kod ravnomernog kružnog kretanja intenzitet brzine je stalan, ali se njen pravac neprekidno menja, pa postoji centripetalno ubrzanje."
            ),

            Question(
                "Mašina izvrši rad 6000 J za 30 s. Kolika je njena snaga?",
                listOf(
                    "20 W",
                    "180 W",
                    "200 W",
                    "600 W"
                ),
                2,
                "Snaga je P = A/t = 6000 J / 30 s = 200 W."
            ),

            Question(
                "Telo mase 1 kg klizi sa visine 5 m bez trenja. Za g ≈ 10 m/s² kinetička energija pri dnu je:",
                listOf(
                    "5 J",
                    "10 J",
                    "25 J",
                    "50 J"
                ),
                3,
                "Bez trenja se mehanička energija održava. Početna potencijalna energija mgh = 1 · 10 · 5 = 50 J prelazi u kinetičku energiju."
            ),

            Question(
                "Za telo koje se kreće ravnomerno ubrzano iz mirovanja važi:",
                listOf(
                    "s je proporcionalno t",
                    "s je proporcionalno t²",
                    "v je proporcionalno t²",
                    "a je proporcionalno t"
                ),
                1,
                "Za ravnomerno ubrzano kretanje iz mirovanja s = at²/2, pa je pređeni put proporcionalan kvadratu vremena."
            ),

            Question(
                "Dva tela imaju jednake impulse. Ako prvo telo ima dvostruko veću masu, njegova brzina je:",
                listOf(
                    "dvostruko veća",
                    "dvostruko manja",
                    "ista",
                    "četiri puta veća"
                ),
                1,
                "Pošto je p = mv, za isti impuls dvostruko veća masa mora imati dvostruko manju brzinu."
            )
        )
    }
}
