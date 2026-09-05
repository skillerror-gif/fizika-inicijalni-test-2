package rs.fizika.inicijalnitest2

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.Base64
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
import kotlin.math.max

data class DiagramSpec(
    val type: String,
    val values: List<Float>,
    val labels: List<String> = emptyList(),
    val xLabel: String = "",
    val yLabel: String = ""
)

data class Question(
    val text: String,
    val options: List<String>,
    val correct: Int,
    val explanation: String,
    val diagram: DiagramSpec? = null
)

class PhysicsDiagramView(context: Context) : View(context) {
    private val blue = Color.parseColor("#1267D8")
    private val darkBlue = Color.parseColor("#123A78")
    private val grid = Color.parseColor("#D6E8FA")
    private val axisPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = darkBlue; strokeWidth = 3f }
    private val gridPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = grid; strokeWidth = 2f }
    private val dataPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = blue; strokeWidth = 7f; style = Paint.Style.STROKE; strokeCap = Paint.Cap.ROUND; strokeJoin = Paint.Join.ROUND }
    private val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = blue; style = Paint.Style.FILL }
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = darkBlue; textSize = 30f; textAlign = Paint.Align.CENTER }
    private var spec: DiagramSpec? = null

    fun setDiagram(value: DiagramSpec?) {
        spec = value
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val d = spec ?: return
        canvas.drawColor(Color.WHITE)
        val left = 74f
        val top = 24f
        val right = width - 26f
        val bottom = height - 62f
        val chartW = max(1f, right - left)
        val chartH = max(1f, bottom - top)

        for (i in 0..4) {
            val y = top + chartH * i / 4f
            canvas.drawLine(left, y, right, y, gridPaint)
        }
        canvas.drawLine(left, top, left, bottom, axisPaint)
        canvas.drawLine(left, bottom, right, bottom, axisPaint)

        val maxValue = max(1f, d.values.maxOrNull() ?: 1f)
        if (d.type == "bar") {
            val count = max(1, d.values.size)
            val slot = chartW / count
            d.values.forEachIndexed { index, value ->
                val h = chartH * (value / maxValue) * 0.86f
                val barW = slot * 0.48f
                val cx = left + slot * (index + 0.5f)
                val rect = RectF(cx - barW / 2f, bottom - h, cx + barW / 2f, bottom)
                canvas.drawRoundRect(rect, 10f, 10f, fillPaint)
                if (index < d.labels.size) canvas.drawText(d.labels[index], cx, bottom + 34f, textPaint)
            }
        } else {
            val count = max(1, d.values.size)
            val path = Path()
            d.values.forEachIndexed { index, value ->
                val x = if (count == 1) left else left + chartW * index / (count - 1f)
                val y = bottom - chartH * (value / maxValue) * 0.86f
                if (index == 0) path.moveTo(x, y) else path.lineTo(x, y)
                canvas.drawCircle(x, y, 7f, fillPaint)
                if (index < d.labels.size) canvas.drawText(d.labels[index], x, bottom + 34f, textPaint)
            }
            canvas.drawPath(path, dataPaint)
        }

        if (d.xLabel.isNotBlank()) canvas.drawText(d.xLabel, (left + right) / 2f, height - 10f, textPaint)
        if (d.yLabel.isNotBlank()) {
            canvas.save()
            canvas.rotate(-90f, 20f, (top + bottom) / 2f)
            canvas.drawText(d.yLabel, 20f, (top + bottom) / 2f, textPaint)
            canvas.restore()
        }
    }
}

class MainActivity : AppCompatActivity() {

    private val blue = Color.parseColor("#1267D8")
    private val darkBlue = Color.parseColor("#123A78")
    private val lightBlue = Color.parseColor("#EAF4FF")
    private val borderBlue = Color.parseColor("#B9D9F7")
    private val textDark = Color.parseColor("#14345E")
    private val muted = Color.parseColor("#58779E")

    private lateinit var heroPanel: LinearLayout
    private lateinit var title: TextView
    private lateinit var subtitle: TextView
    private lateinit var introText: TextView
    private lateinit var startButton: Button
    private lateinit var quotePanel: LinearLayout
    private lateinit var luckText: TextView
    private lateinit var progress: TextView
    private lateinit var questionText: TextView
    private lateinit var diagramView: PhysicsDiagramView
    private lateinit var optionsGroup: RadioGroup
    private lateinit var optionButtons: List<RadioButton>
    private lateinit var explanationText: TextView
    private lateinit var nextButton: Button
    private lateinit var resultText: TextView
    private lateinit var restartButton: Button
    private lateinit var timerText: TextView

    private var questions: List<Question> = emptyList()
    private var currentQuestion = 0
    private var score = 0
    private var answerChecked = false

    private val timerHandler = Handler(Looper.getMainLooper())
    private var testStartTime = 0L
    private var elapsedMillis = 0L
    private var timerRunning = false

    private val timerRunnable = object : Runnable {
        override fun run() {
            if (!timerRunning) return
            elapsedMillis = SystemClock.elapsedRealtime() - testStartTime
            timerText.text = "⏱  Vreme: ${formatTime(elapsedMillis)}"
            timerHandler.postDelayed(this, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.WHITE
        window.navigationBarColor = Color.WHITE
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(dp(22), dp(20), dp(22), dp(28))
            setBackgroundColor(Color.WHITE)
        }

        heroPanel = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(dp(18), dp(15), dp(18), dp(15))
            background = GradientDrawable(GradientDrawable.Orientation.TL_BR, intArrayOf(Color.parseColor("#083E86"), Color.parseColor("#1685F0"))).apply {
                cornerRadius = dp(28).toFloat()
            }
            elevation = dp(7).toFloat()
            addView(TextView(this@MainActivity).apply {
                text = "FIZIKA  2"
                textSize = 34f
                setTextColor(Color.WHITE)
                setTypeface(Typeface.DEFAULT_BOLD)
                gravity = Gravity.CENTER
            }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
            addView(TextView(this@MainActivity).apply {
                text = "⚛"
                textSize = 58f
                setTextColor(Color.WHITE)
                gravity = Gravity.CENTER
            }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
            addView(TextView(this@MainActivity).apply {
                text = "▰  ▰  ▰"
                textSize = 25f
                setTextColor(Color.WHITE)
                gravity = Gravity.CENTER
            }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
        }
        root.addView(heroPanel, LinearLayout.LayoutParams(dp(220), dp(220)).apply { bottomMargin = dp(20) })

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
            text = "30 nasumično izabranih pitanja iz gradiva prvog razreda"
            textSize = 16f
            setTextColor(muted)
            gravity = Gravity.CENTER
            setLineSpacing(0f, 1.15f)
        }
        root.addView(introText, matchWrap(bottom = 22))

        startButton = Button(this).apply {
            text = "Počni test  →"
            textSize = 19f
            setTextColor(Color.WHITE)
            setTypeface(Typeface.DEFAULT_BOLD)
            gravity = Gravity.CENTER
            isAllCaps = false
            background = rounded(blue, blue, 18f, 1)
            elevation = dp(5).toFloat()
        }
        root.addView(startButton, matchHeight(dp(64), bottom = 22))

        quotePanel = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(dp(10), dp(10), dp(12), dp(10))
            background = rounded(lightBlue, Color.TRANSPARENT, 20f, 0)
        }
        val teslaImage = ImageView(this).apply {
            val bytes = Base64.decode(TESLA_B64, Base64.DEFAULT)
            setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.size))
            scaleType = ImageView.ScaleType.CENTER_CROP
            background = rounded(Color.WHITE, borderBlue, 18f, 1)
            clipToOutline = true
        }
        quotePanel.addView(teslaImage, LinearLayout.LayoutParams(dp(120), dp(120)).apply { marginEnd = dp(12) })
        quotePanel.addView(TextView(this).apply {
            text = "„Ako želiš da pronađeš tajne svemira, misli u terminima energije, frekvencije i vibracije.“\n\nNikola Tesla"
            textSize = 15f
            setTextColor(darkBlue)
            setTypeface(Typeface.create(Typeface.SERIF, Typeface.ITALIC))
            gravity = Gravity.CENTER
        }, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f))
        root.addView(quotePanel, matchWrap(bottom = 14))

        luckText = TextView(this).apply {
            text = "Srećno! Pažljivo pročitaj svako pitanje."
            textSize = 15f
            setTextColor(muted)
            gravity = Gravity.CENTER
            setPadding(dp(14), dp(14), dp(14), dp(14))
            background = rounded(lightBlue, Color.TRANSPARENT, 18f, 0)
        }
        root.addView(luckText, matchWrap(bottom = 8))

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

        diagramView = PhysicsDiagramView(this).apply {
            visibility = View.GONE
            background = rounded(Color.WHITE, borderBlue, 18f, 1)
            elevation = dp(4).toFloat()
        }
        root.addView(diagramView, matchHeight(dp(245), bottom = 14))

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
                optionsGroup.addView(this, RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT).apply {
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

        timerText = TextView(this).apply {
            text = "⏱  Vreme: 00:00"
            textSize = 17f
            setTextColor(darkBlue)
            setTypeface(Typeface.DEFAULT_BOLD)
            gravity = Gravity.CENTER
            visibility = View.GONE
            setPadding(dp(12), dp(13), dp(12), dp(13))
            background = rounded(lightBlue, borderBlue, 0f, 1)
        }

        val screen = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.WHITE)
            addView(scrollView, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f))
            addView(timerText, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
        }
        setContentView(screen)

        startButton.setOnClickListener { startTest() }
        nextButton.setOnClickListener { goToNextQuestion() }
        restartButton.setOnClickListener { showStartScreen() }
    }

    private fun startTest() {
        questions = MixedQuestionBank.buildTest30()
        currentQuestion = 0
        score = 0
        heroPanel.visibility = View.GONE
        introText.visibility = View.GONE
        startButton.visibility = View.GONE
        quotePanel.visibility = View.GONE
        luckText.visibility = View.GONE
        subtitle.text = "Test u toku"
        resultText.visibility = View.GONE
        restartButton.visibility = View.GONE
        progress.visibility = View.VISIBLE
        questionText.visibility = View.VISIBLE
        optionsGroup.visibility = View.VISIBLE
        timerText.visibility = View.VISIBLE
        startTimer()
        showQuestion()
    }

    private fun startTimer() {
        timerHandler.removeCallbacks(timerRunnable)
        elapsedMillis = 0L
        testStartTime = SystemClock.elapsedRealtime()
        timerRunning = true
        timerText.text = "⏱  Vreme: 00:00"
        timerHandler.post(timerRunnable)
    }

    private fun stopTimer() {
        if (timerRunning) {
            elapsedMillis = SystemClock.elapsedRealtime() - testStartTime
            timerRunning = false
            timerHandler.removeCallbacks(timerRunnable)
        }
    }

    private fun formatTime(milliseconds: Long): String {
        val totalSeconds = milliseconds / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return String.format("%02d:%02d", minutes, seconds)
    }

    private fun showQuestion() {
        val question = questions[currentQuestion]
        answerChecked = false
        val percent = ((currentQuestion + 1) * 100) / questions.size
        progress.text = "Pitanje ${currentQuestion + 1} / ${questions.size}     •     $percent%"
        questionText.text = question.text
        if (question.diagram != null) {
            diagramView.setDiagram(question.diagram)
            diagramView.visibility = View.VISIBLE
        } else {
            diagramView.visibility = View.GONE
        }
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
        stopTimer()
        progress.visibility = View.GONE
        questionText.visibility = View.GONE
        diagramView.visibility = View.GONE
        optionsGroup.visibility = View.GONE
        explanationText.visibility = View.GONE
        nextButton.visibility = View.GONE
        timerText.visibility = View.GONE
        val percent = score * 100 / questions.size
        val grade = when {
            percent >= 90 -> "Odlično!"
            percent >= 75 -> "Vrlo dobro!"
            percent >= 60 -> "Dobro!"
            percent >= 45 -> "Solidno!"
            else -> "Pokušaj ponovo!"
        }
        subtitle.text = "Inicijalni test"
        resultText.text = "🏆\n\n$grade\n\n$percent%\n\n$score / ${questions.size} tačnih odgovora\n\n⏱ Vreme izrade: ${formatTime(elapsedMillis)}"
        resultText.visibility = View.VISIBLE
        restartButton.visibility = View.VISIBLE
    }

    private fun showStartScreen() {
        stopTimer()
        subtitle.text = "Inicijalni test"
        heroPanel.visibility = View.VISIBLE
        introText.visibility = View.VISIBLE
        startButton.visibility = View.VISIBLE
        quotePanel.visibility = View.VISIBLE
        luckText.visibility = View.VISIBLE
        progress.visibility = View.GONE
        questionText.visibility = View.GONE
        diagramView.visibility = View.GONE
        optionsGroup.visibility = View.GONE
        explanationText.visibility = View.GONE
        nextButton.visibility = View.GONE
        resultText.visibility = View.GONE
        restartButton.visibility = View.GONE
        timerText.visibility = View.GONE
    }

    override fun onDestroy() {
        timerHandler.removeCallbacks(timerRunnable)
        super.onDestroy()
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
        LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply { bottomMargin = dp(bottom) }

    private fun matchHeight(height: Int, bottom: Int = 0): LinearLayout.LayoutParams =
        LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height).apply { bottomMargin = dp(bottom) }

    private fun dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()

    companion object {
        private const val TESLA_B64 = "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..."
    }
}
