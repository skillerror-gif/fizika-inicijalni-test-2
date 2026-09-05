package rs.fizika.inicijalnitest2

import android.content.Context
import android.content.res.ColorStateList
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
import android.view.Gravity
import android.view.View
import android.widget.Button
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

class TeslaPortraitView(context: Context) : View(context) {
    private val navy = Color.parseColor("#123A78")
    private val pale = Color.parseColor("#DCEEFF")
    private val skin = Color.parseColor("#EEF5FB")
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(pale)
        val w = width.toFloat()
        val h = height.toFloat()

        paint.color = Color.parseColor("#0A2F63")
        canvas.drawOval(RectF(w * .18f, h * .10f, w * .82f, h * .76f), paint)

        paint.color = skin
        canvas.drawOval(RectF(w * .27f, h * .19f, w * .75f, h * .74f), paint)

        paint.color = Color.parseColor("#1A3152")
        val hair = Path().apply {
            moveTo(w * .23f, h * .33f)
            cubicTo(w * .25f, h * .10f, w * .63f, h * .07f, w * .78f, h * .25f)
            cubicTo(w * .62f, h * .18f, w * .45f, h * .22f, w * .31f, h * .38f)
            close()
        }
        canvas.drawPath(hair, paint)

        paint.color = navy
        paint.strokeWidth = max(2f, w * .025f)
        canvas.drawLine(w * .38f, h * .43f, w * .45f, h * .43f, paint)
        canvas.drawLine(w * .58f, h * .43f, w * .65f, h * .43f, paint)

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = max(3f, w * .032f)
        canvas.drawArc(RectF(w * .39f, h * .53f, w * .62f, h * .66f), 198f, 144f, false, paint)
        paint.style = Paint.Style.FILL

        paint.color = Color.parseColor("#203B62")
        canvas.drawOval(RectF(w * .39f, h * .55f, w * .62f, h * .61f), paint)

        paint.color = Color.parseColor("#FFFFFF")
        val shirt = Path().apply {
            moveTo(w * .20f, h * .80f)
            lineTo(w * .50f, h * .64f)
            lineTo(w * .80f, h * .80f)
            lineTo(w * .92f, h)
            lineTo(w * .08f, h)
            close()
        }
        canvas.drawPath(shirt, paint)

        paint.color = Color.parseColor("#173961")
        val jacketL = Path().apply {
            moveTo(0f, h)
            lineTo(w * .10f, h * .77f)
            lineTo(w * .43f, h * .66f)
            lineTo(w * .36f, h)
            close()
        }
        canvas.drawPath(jacketL, paint)
        val jacketR = Path().apply {
            moveTo(w, h)
            lineTo(w * .90f, h * .77f)
            lineTo(w * .57f, h * .66f)
            lineTo(w * .64f, h)
            close()
        }
        canvas.drawPath(jacketR, paint)

        paint.color = navy
        paint.textAlign = Paint.Align.CENTER
        paint.typeface = Typeface.DEFAULT_BOLD
        paint.textSize = w * .13f
        canvas.drawText("N. TESLA", w * .5f, h * .96f, paint)
    }
}

class PhysicsDiagramView(context: Context) : View(context) {
    private val blue = Color.parseColor("#1267D8")
    private val darkBlue = Color.parseColor("#123A78")
    private val grid = Color.parseColor("#D6E8FA")
    private val axisPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = darkBlue; strokeWidth = 3f }
    private val gridPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = grid; strokeWidth = 2f }
    private val dataPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = blue; strokeWidth = 7f; style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND; strokeJoin = Paint.Join.ROUND
    }
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
        quotePanel.addView(TeslaPortraitView(this), LinearLayout.LayoutParams(dp(120), dp(120)).apply { marginEnd = dp(12) })
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
}
