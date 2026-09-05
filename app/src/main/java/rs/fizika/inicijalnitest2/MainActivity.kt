package rs.fizika.inicijalnitest2

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageView
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

    private val blue = Color.parseColor("#1267D8")
    private val darkBlue = Color.parseColor("#123A78")
    private val lightBlue = Color.parseColor("#EAF4FF")
    private val borderBlue = Color.parseColor("#B9D9F7")
    private val textDark = Color.parseColor("#14345E")
    private val muted = Color.parseColor("#58779E")

    private lateinit var heroIcon: ImageView
    private lateinit var title: TextView
    private lateinit var subtitle: TextView
    private lateinit var introText: TextView
    private lateinit var difficultyLabel: TextView
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
    private lateinit var quoteText: TextView

    private var questions: List<Question> = emptyList()
    private var currentQuestion = 0
    private var score = 0
    private var selectedLevel = ""
    private var answerChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = Color.WHITE
        window.navigationBarColor = Color.WHITE
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(dp(22), dp(22), dp(22), dp(30))
            setBackgroundColor(Color.WHITE)
        }

        heroIcon = ImageView(this).apply {
            setImageResource(R.drawable.ic_launcher)
            scaleType = ImageView.ScaleType.CENTER_CROP
            background = rounded(Color.WHITE, borderBlue, 22f, 1)
            elevation = dp(8).toFloat()
        }
        root.addView(heroIcon, LinearLayout.LayoutParams(dp(170), dp(170)).apply {
            bottomMargin = dp(22)
        })

        title = TextView(this).apply {
            text = "Fizika 2"
            textSize = 34f
            setTextColor(darkBlue)
            setTypeface(Typeface.DEFAULT_BOLD)
            gravity = Gravity.CENTER
        }
        root.addView(title, matchWrap(bottom = 2))

        subtitle = TextView(this).apply {
            text = "Inicijalni test"
            textSize = 23f
            setTextColor(blue)
            setTypeface(Typeface.DEFAULT_BOLD)
            gravity = Gravity.CENTER
        }
        root.addView(subtitle, matchWrap(bottom = 14))

        introText = TextView(this).apply {
            text = "Proveri znanje iz prethodnog razreda\ni pripremi se za novi nivo!"
            textSize = 16f
            setTextColor(muted)
            gravity = Gravity.CENTER
            setLineSpacing(0f, 1.15f)
        }
        root.addView(introText, matchWrap(bottom = 24))

        difficultyLabel = TextView(this).apply {
            text = "Izaberi težinu testa"
            textSize = 18f
            setTextColor(textDark)
            setTypeface(Typeface.DEFAULT_BOLD)
            gravity = Gravity.START
        }
        root.addView(difficultyLabel, matchWrap(bottom = 12))

        easyButton = Button(this).apply {
            text = "🎓   Lakši test\nOsnovna pitanja za proveru ključnih pojmova   ›"
            textSize = 17f
            setTextColor(darkBlue)
            gravity = Gravity.START or Gravity.CENTER_VERTICAL
            isAllCaps = false
            setPadding(dp(18), dp(16), dp(18), dp(16))
            background = cardBackground()
            elevation = dp(5).toFloat()
        }
        root.addView(easyButton, matchHeight(dp(100), bottom = 14))

        hardButton = Button(this).apply {
            text = "📊   Teži test\nSloženija pitanja za dublju proveru znanja   ›"
            textSize = 17f
            setTextColor(darkBlue)
            gravity = Gravity.START or Gravity.CENTER_VERTICAL
            isAllCaps = false
            setPadding(dp(18), dp(16), dp(18), dp(16))
            background = cardBackground()
            elevation = dp(5).toFloat()
        }
        root.addView(hardButton, matchHeight(dp(100), bottom = 24))

        quoteText = TextView(this).apply {
            text = "„Znanje je sila koja pokreće svet.“\n— N. Tesla"
            textSize = 15f
            setTextColor(darkBlue)
            gravity = Gravity.CENTER
            setPadding(dp(18), dp(16), dp(18), dp(16))
            background = rounded(lightBlue, Color.TRANSPARENT, 18f, 0)
        }
        root.addView(quoteText, matchWrap(bottom = 8))

        progress = TextView(this).apply {
            textSize = 17f
            setTextColor(darkBlue)
            setTypeface(Typeface.DEFAULT_BOLD)
            gravity = Gravity.CENTER
            visibility = View.GONE
            setPadding(dp(12), dp(12), dp(12), dp(12))
            background = rounded(lightBlue, borderBlue, 16f, 1)
        }
        root.addView(progress, matchWrap(bottom = 14))

        questionText = TextView(this).apply {
            textSize = 21f
            setTextColor(textDark)
            setTypeface(Typeface.DEFAULT_BOLD)
            visibility = View.GONE
            setPadding(dp(20), dp(22), dp(20), dp(22))
            background = cardBackground()
            elevation = dp(5).toFloat()
        }
        root.addView(questionText, matchWrap(bottom = 14))

        optionsGroup = RadioGroup(this).apply {
            orientation = RadioGroup.VERTICAL
            visibility = View.GONE
        }
        root.addView(optionsGroup, matchWrap(bottom = 10))

        optionButtons = List(4) { index ->
            RadioButton(this).apply {
                id = View.generateViewId()
                textSize = 17f
                setTextColor(textDark)
                buttonTintList = ColorStateList(
                    arrayOf(intArrayOf(android.R.attr.state_checked), intArrayOf()),
                    intArrayOf(blue, blue)
                )
                setPadding(dp(15), dp(12), dp(15), dp(12))
                background = answerBackground()
                setOnClickListener { checkAnswer() }
                optionsGroup.addView(this, RadioGroup.LayoutParams(
                    RadioGroup.LayoutParams.MATCH_PARENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    bottomMargin = if (index == 3) 0 else dp(10)
                })
            }
        }

        explanationText = TextView(this).apply {
            textSize = 16f
            setTextColor(textDark)
            visibility = View.GONE
            setPadding(dp(16), dp(16), dp(16), dp(16))
            background = rounded(lightBlue, borderBlue, 16f, 1)
        }
        root.addView(explanationText, matchWrap(bottom = 14))

        nextButton = Button(this).apply {
            text = "Sledeće  →"
            textSize = 17f
            setTextColor(Color.WHITE)
            setTypeface(Typeface.DEFAULT_BOLD)
            isAllCaps = false
            visibility = View.GONE
            background = rounded(blue, blue, 18f, 1)
            elevation = dp(5).toFloat()
        }
        root.addView(nextButton, matchHeight(dp(58), bottom = 10))

        resultText = TextView(this).apply {
            textSize = 22f
            setTextColor(darkBlue)
            setTypeface(Typeface.DEFAULT_BOLD)
            gravity = Gravity.CENTER
            visibility = View.GONE
            setPadding(dp(20), dp(28), dp(20), dp(28))
            background = cardBackground()
            elevation = dp(6).toFloat()
        }
        root.addView(resultText, matchWrap(bottom = 16))

        restartButton = Button(this).apply {
            text = "↻  Ponovi test"
            textSize = 17f
            setTextColor(blue)
            setTypeface(Typeface.DEFAULT_BOLD)
            isAllCaps = false
            visibility = View.GONE
            background = rounded(Color.WHITE, blue, 18f, 2)
        }
        root.addView(restartButton, matchHeight(dp(58), bottom = 10))

        val scrollView = ScrollView(this).apply {
            setBackgroundColor(Color.WHITE)
            addView(root)
        }
        setContentView(scrollView)

        easyButton.setOnClickListener { startTest(QuestionBank.easyQuestions(), "Lakši test") }
        hardButton.setOnClickListener { startTest(QuestionBank.hardQuestions(), "Teži test") }
        nextButton.setOnClickListener { goToNextQuestion() }
        restartButton.setOnClickListener { showStartScreen() }
    }

    private fun startTest(questionList: List<Question>, level: String) {
        questions = questionList
        selectedLevel = level
        currentQuestion = 0
        score = 0
        heroIcon.visibility = View.GONE
        introText.visibility = View.GONE
        difficultyLabel.visibility = View.GONE
        easyButton.visibility = View.GONE
        hardButton.visibility = View.GONE
        quoteText.visibility = View.GONE
        subtitle.text = level
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
        val percent = ((currentQuestion + 1) * 100) / questions.size
        progress.text = "Pitanje ${currentQuestion + 1} / ${questions.size}     •     $percent%"
        questionText.text = question.text
        optionsGroup.clearCheck()
        optionButtons.forEachIndexed { index, button ->
            val letter = ('A'.code + index).toChar()
            button.text = "   $letter     ${question.options[index]}"
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
        val selectedAnswer = optionButtons.indexOfFirst { it.id == selectedId }
        val question = questions[currentQuestion]
        val isCorrect = selectedAnswer == question.correct
        if (isCorrect) score++
        answerChecked = true
        optionButtons.forEach { it.isEnabled = false }
        explanationText.text = if (isCorrect) {
            "✓  Tačan odgovor!\n\n${question.explanation}"
        } else {
            "✗  Netačan odgovor.\n\nTačan odgovor: ${question.options[question.correct]}\n\n${question.explanation}"
        }
        explanationText.visibility = View.VISIBLE
        nextButton.text = if (currentQuestion == questions.lastIndex) "Prikaži rezultat  →" else "Sledeće  →"
        nextButton.visibility = View.VISIBLE
    }

    private fun goToNextQuestion() {
        if (!answerChecked) return
        if (currentQuestion < questions.lastIndex) {
            currentQuestion++
            showQuestion()
        } else showResult()
    }

    private fun showResult() {
        progress.visibility = View.GONE
        questionText.visibility = View.GONE
        optionsGroup.visibility = View.GONE
        explanationText.visibility = View.GONE
        nextButton.visibility = View.GONE
        val percent = score * 100 / questions.size
        val grade = when {
            percent >= 90 -> "Odlično!"
            percent >= 75 -> "Vrlo dobro!"
            percent >= 60 -> "Dobro!"
            percent >= 45 -> "Solidno!"
            else -> "Pokušaj ponovo!"
        }
        subtitle.text = "Inicijalni test"
        resultText.text = "🏆\n\n$grade\n\n$percent%\n\n$score / ${questions.size} tačnih odgovora\n\n$selectedLevel"
        resultText.visibility = View.VISIBLE
        restartButton.visibility = View.VISIBLE
    }

    private fun showStartScreen() {
        subtitle.text = "Inicijalni test"
        heroIcon.visibility = View.VISIBLE
        introText.visibility = View.VISIBLE
        difficultyLabel.visibility = View.VISIBLE
        easyButton.visibility = View.VISIBLE
        hardButton.visibility = View.VISIBLE
        quoteText.visibility = View.VISIBLE
        progress.visibility = View.GONE
        questionText.visibility = View.GONE
        optionsGroup.visibility = View.GONE
        explanationText.visibility = View.GONE
        nextButton.visibility = View.GONE
        resultText.visibility = View.GONE
        restartButton.visibility = View.GONE
    }

    private fun cardBackground() = rounded(Color.WHITE, borderBlue, 20f, 1)

    private fun answerBackground(): StateListDrawable {
        val selected = rounded(lightBlue, blue, 18f, 2)
        val normal = rounded(Color.WHITE, borderBlue, 18f, 1)
        return StateListDrawable().apply {
            addState(intArrayOf(android.R.attr.state_checked), selected)
            addState(intArrayOf(), normal)
        }
    }

    private fun rounded(fill: Int, stroke: Int, radiusDp: Float, strokeDp: Int): GradientDrawable =
        GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(fill)
            cornerRadius = dp(radiusDp.toInt()).toFloat()
            if (strokeDp > 0 && stroke != Color.TRANSPARENT) setStroke(dp(strokeDp), stroke)
        }

    private fun matchWrap(bottom: Int = 0): LinearLayout.LayoutParams =
        LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
            bottomMargin = dp(bottom)
        }

    private fun matchHeight(height: Int, bottom: Int = 0): LinearLayout.LayoutParams =
        LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height).apply {
            bottomMargin = dp(bottom)
        }

    private fun dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()
}
