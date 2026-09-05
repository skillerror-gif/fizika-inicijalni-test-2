package rs.fizika.inicijalnitest2

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

data class Question(
    val text: String,
    val options: List<String>,
    val correct: Int
)

class MainActivity : AppCompatActivity() {

    private lateinit var title: TextView
    private lateinit var subtitle: TextView
    private lateinit var easyButton: Button
    private lateinit var hardButton: Button
    private lateinit var progress: TextView
    private lateinit var questionText: TextView
    private lateinit var optionsGroup: RadioGroup
    private lateinit var optionButtons: List<RadioButton>
    private lateinit var nextButton: Button
    private lateinit var resultText: TextView
    private lateinit var restartButton: Button

    private var questions: List<Question> = emptyList()
    private var index = 0
    private var score = 0
    private var levelName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)
        }

        title = TextView(this).apply {
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
        }

        questionText = TextView(this).apply {
            textSize = 20f
            setPadding(0, 20, 0, 20)
            visibility = View.GONE
        }

        optionsGroup = RadioGroup(this).apply {
            visibility = View.GONE
        }

        optionButtons = List(4) {
            RadioButton(this).apply {
                textSize = 17f
                optionsGroup.addView(this)
            }
        }

        nextButton = Button(this).apply {
            text = "Sledeće"
            visibility = View.GONE
        }

        resultText = TextView(this).apply {
            textSize = 22f
            textAlignment = View.TEXT_ALIGNMENT_CENTER
            setPadding(0, 28, 0, 20)
            visibility = View.GONE
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
        root.addView(nextButton)
        root.addView(resultText)
        root.addView(restartButton)

        val scroll = ScrollView(this)
        scroll.addView(root)
        setContentView(scroll)

        easyButton.setOnClickListener {
            startTest(easyQuestions(), "Lakši nivo")
        }

        hardButton.setOnClickListener {
            startTest(hardQuestions(), "Teži nivo")
        }

        nextButton.setOnClickListener {
            submitAnswer()
        }

        restartButton.setOnClickListener {
            resetToStart()
        }
    }

    private fun startTest(list: List<Question>, level: String) {
        questions = list
        levelName = level
        index = 0
        score = 0

        subtitle.text = level

        easyButton.visibility = View.GONE
        hardButton.visibility = View.GONE
        resultText.visibility = View.GONE
        restartButton.visibility = View.GONE

        progress.visibility = View.VISIBLE
        questionText.visibility = View.VISIBLE
        optionsGroup.visibility = View.VISIBLE
        nextButton.visibility = View.VISIBLE

        showQuestion()
    }

    private fun showQuestion() {
        val q = questions[index]

        progress.text = "Pitanje ${index + 1} / ${questions.size}"
        questionText.text = q.text

        optionsGroup.clearCheck()

        optionButtons.forEachIndexed { i, button ->
            button.text = q.options[i]
        }

        nextButton.text =
            if (index == questions.lastIndex) "Završi test"
            else "Sledeće"
    }

    private fun submitAnswer() {
        val selectedId = optionsGroup.checkedRadioButtonId

        if (selectedId == -1) {
            Toast.makeText(
                this,
                "Izaberi jedan odgovor.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val selectedIndex =
            optionButtons.indexOfFirst { it.id == selectedId }

        if (selectedIndex == questions[index].correct) {
            score++
        }

        if (index < questions.lastIndex) {
            index++
            showQuestion()
        } else {
            showResult()
        }
    }

    private fun showResult() {
        progress.visibility = View.GONE
        questionText.visibility = View.GONE
        optionsGroup.visibility = View.GONE
        nextButton.visibility = View.GONE

        val percent = score * 100 / questions.size

        resultText.text =
            "$levelName\n\nTačno: $score / ${questions.size}\nUspešnost: $percent%"

        resultText.visibility = View.VISIBLE
        restartButton.visibility = View.VISIBLE
    }

    private fun resetToStart() {
        subtitle.text = "Izaberi nivo"

        easyButton.visibility = View.VISIBLE
        hardButton.visibility = View.VISIBLE

        resultText.visibility = View.GONE
        restartButton.visibility = View.GONE
    }

    private fun easyQuestions() = listOf(

        Question(
            "Telo pređe 120 m za 20 s. Kolika je srednja brzina?",
            listOf("4 m/s", "6 m/s", "8 m/s", "10 m/s"),
            1
        ),

        Question(
            "Koja je SI jedinica za ubrzanje?",
            listOf("m/s", "m/s²", "N", "J"),
            1
        ),

        Question(
            "Ako se brzina tela ne menja, ubrzanje je:",
            listOf(
                "pozitivno",
                "negativno",
                "jednako nuli",
                "uvek 9,81 m/s²"
            ),
            2
        ),

        Question(
            "Drugi Njutnov zakon zapisuje se kao:",
            listOf("F = ma", "p = mv", "A = Fs", "P = A/t"),
            0
        ),

        Question(
            "Na telo mase 2 kg deluje sila 10 N. Koliko je ubrzanje?",
            listOf("2 m/s²", "5 m/s²", "10 m/s²", "20 m/s²"),
            1
        ),

        Question(
            "Ako je rezultanta svih sila na telo jednaka nuli, telo:",
            listOf(
                "mora da miruje",
                "mora da ubrzava",
                "miruje ili se kreće ravnomerno pravolinijski",
                "mora da se kreće kružno"
            ),
            2
        ),

        Question(
            "Koja od navedenih veličina je vektorska?",
            listOf("masa", "vreme", "put", "brzina"),
            3
        ),

        Question(
            "Rad sile u pravcu pomeranja dat je izrazom:",
            listOf("A = F/s", "A = Fs", "A = mv", "A = Pt²"),
            1
        ),

        Question(
            "SI jedinica za rad i energiju je:",
            listOf("vat", "džul", "njutn", "paskal"),
            1
        ),

        Question(
            "Kinetička energija tela mase m i brzine v je:",
            listOf("mv", "mv²", "mv²/2", "mgh"),
            2
        ),

        Question(
            "Gravitaciona potencijalna energija blizu Zemlj
