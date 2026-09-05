package rs.fizika.inicijalnitest2

object QuestionBank {
    const val EASY = "EASY"
    const val HARD = "HARD"

    private fun Question(
        text: String, options: List<String>, correct: Int, explanation: String,
        area: String, subtopic: String, difficulty: String
    ): Question = rs.fizika.inicijalnitest2.Question(text, options, correct, explanation)

    private fun allQuestions(): List<Question> = listOf(
        Question("Koja od navedenih fizičkih veličina je vektorska?", listOf("masa", "vreme", "pomeraj", "temperatura"), 2, "Pomeraj ima intenzitet, pravac i smer.", "Uvod", "Vektori", EASY),
        Question("Koja je osnovna SI jedinica za dužinu?", listOf("centimetar", "metar", "kilometar", "milimetar"), 1, "Metar (m) je osnovna SI jedinica za dužinu.", "Uvod", "SI jedinice", EASY),
        Question("Koja od navedenih veličina je skalarna?", listOf("masa", "sila", "ubrzanje", "brzina"), 0, "Masa je skalarna veličina; određena je samo brojnom vrednošću i jedinicom.", "Uvod", "Skalari i vektori", EASY),
        Question("Rezultanta dva vektora istog pravca i smera, intenziteta 3 N i 5 N, iznosi:", listOf("2 N", "8 N", "15 N", "1,67 N"), 1, "Za vektore istog pravca i smera intenziteti se sabiraju: 3 N + 5 N = 8 N.", "Uvod", "Sabiranje vektora", EASY),
        Question("Rezultanta sila 8 N udesno i 3 N ulevo je:", listOf("5 N udesno", "11 N udesno", "5 N ulevo", "24 N"), 0, "Sile su suprotnih smerova, pa je rezultanta 8 N - 3 N = 5 N udesno.", "Uvod", "Sabiranje vektora", HARD),
        Question("Dva međusobno normalna vektora imaju intenzitete 3 i 4. Intenzitet njihovog zbira je:", listOf("1", "3,5", "5", "7"), 2, "Za normalne vektore koristi se Pitagorina teorema: √(3²+4²)=5.", "Uvod", "Sabiranje vektora", HARD),
        Question("Brzina 72 km/h izražena u m/s iznosi:", listOf("10 m/s", "20 m/s", "36 m/s", "72 m/s"), 1, "Brzina u km/h deli se sa 3,6: 72/3,6 = 20 m/s.", "Uvod", "Pretvaranje jedinica", HARD),
        Question("Rezultanta dva jednaka vektora suprotnih smerova je:", listOf("jednaka jednom vektoru", "dvostruko veća", "jednaka nuli", "ne može se odrediti"), 2, "Jednaki vektori suprotnih smerova međusobno se poništavaju.", "Uvod", "Vektori", HARD),
        Question("Put je:", listOf("skalarna veličina", "vektorska veličina", "sila", "ubrzanje"), 0, "Put je skalarna veličina.", "Kinematika", "Put i pomeraj", EASY),
        Question("Pomeraj predstavlja promenu:", listOf("mase", "položaja tela", "energije", "ubrzanja"), 1, "Pomeraj je vektor koji spaja početni i krajnji položaj tela.", "Kinematika", "Put i pomeraj", EASY),
        Question("Telo pređe 150 m za 30 s. Srednja brzina je:", listOf("3 m/s", "5 m/s", "30 m/s", "4500 m/s"), 1, "v = s/t = 150/30 = 5 m/s.", "Kinematika", "Srednja brzina", EASY),
        Question("Pri ravnomernom pravolinijskom kretanju brzina je:", listOf("rastuća", "opadajuća", "konstantna", "uvek nula"), 2, "Kod ravnomernog pravolinijskog kretanja vektor brzine je konstantan.", "Kinematika", "Ravnomerno kretanje", EASY),
        Question("Nagib x–t grafika predstavlja:", listOf("ubrzanje", "brzinu", "silu", "energiju"), 1, "Nagib grafika položaja u funkciji vremena predstavlja brzinu.", "Kinematika", "Grafici kretanja", EASY),
        Question("Nagib v–t grafika predstavlja:", listOf("ubrzanje", "pomeraj", "put", "silu"), 0, "Nagib grafika brzine u funkciji vremena predstavlja ubrzanje.", "Kinematika", "Grafici kretanja", EASY),
        Question("Površina ispod v–t grafika predstavlja:", listOf("ubrzanje", "pomeraj", "silu", "snagu"), 1, "Algebarska površina ispod v–t grafika jednaka je pomeraju.", "Kinematika", "Grafici kretanja", EASY),
        Question("SI jedinica za ubrzanje je:", listOf("m/s", "m/s²", "N", "J"), 1, "Ubrzanje je promena brzine po vremenu, pa je jedinica m/s².", "Kinematika", "Ubrzanje", EASY),
        Question("Ako se brzina ne menja ni po intenzitetu ni po pravcu, ubrzanje je:", listOf("g", "0", "1 m/s²", "beskonačno"), 1, "Bez promene vektora brzine ubrzanje je nula.", "Kinematika", "Ubrzanje", EASY),
        Question("Automobil poveća brzinu sa 10 m/s na 20 m/s za 5 s. Ubrzanje je:", listOf("1 m/s²", "2 m/s²", "5 m/s²", "6 m/s²"), 1, "a = Δv/Δt = (20-10)/5 = 2 m/s².", "Kinematika", "Ubrzanje", EASY),
        Question("Telo kreće iz mirovanja ubrzanjem 3 m/s². Posle 4 s brzina je:", listOf("7 m/s", "9 m/s", "12 m/s", "24 m/s"), 2, "v = at = 3·4 = 12 m/s.", "Kinematika", "Ravnomerno ubrzano kretanje", EASY),
        Question("Telo se kreće 15 m/s i usporava 3 m/s². Za koliko vremena se zaustavlja?", listOf("3 s", "5 s", "12 s", "45 s"), 1, "0 = 15 - 3t, pa je t = 5 s.", "Kinematika", "Ravnomerno promenljivo kretanje", HARD),
        Question("Za kretanje iz mirovanja sa konstantnim ubrzanjem važi:", listOf("s je proporcionalno t", "s je proporcionalno t²", "s je proporcionalno 1/t", "s je konstantno"), 1, "s = at²/2, pa je put proporcionalan kvadratu vremena.", "Kinematika", "Ravnomerno ubrzano kretanje", HARD),
        Question("Slobodan pad bez otpora vazduha je:", listOf("ravnomerno kretanje", "ravnomerno ubrzano kretanje", "kružno kretanje", "oscilatorno kretanje"), 1, "Ubrzanje slobodnog pada je približno konstantno i jednako g.", "Kinematika", "Slobodan pad", HARD),
        Question("Za g≈10 m/s² telo koje slobodno pada 3 s dostiže brzinu približno:", listOf("3 m/s", "10 m/s", "30 m/s", "90 m/s"), 2, "v = gt = 10·3 = 30 m/s.", "Kinematika", "Slobodan pad", HARD),
        Question("Telo bačeno vertikalno naviše u najvišoj tački ima brzinu:", listOf("0", "g", "10 m/s", "beskonačnu"), 0, "U najvišoj tački trenutna brzina je nula, ali ubrzanje ostaje g naniže.", "Kinematika", "Vertikalni hitac", HARD),
        Question("Putnik hoda 2 m/s u smeru voza koji se kreće 10 m/s. U odnosu na tlo putnik se kreće:", listOf("5 m/s", "8 m/s", "12 m/s", "20 m/s"), 2, "Za kolinearne brzine istog smera brzine se sabiraju: 10+2=12 m/s.", "Kinematika", "Relativnost kretanja", HARD),
        Question("Kod ravnomernog kružnog kretanja konstantan je:", listOf("intenzitet brzine", "vektor brzine", "vektor ubrzanja", "položaj"), 0, "Intenzitet brzine je stalan, ali se njen pravac menja.", "Kinematika", "Kružno kretanje", HARD),
        Question("Centripetalno ubrzanje usmereno je:", listOf("tangentno", "od centra", "ka centru kružnice", "vertikalno"), 2, "Centripetalno ubrzanje je uvek usmereno ka centru kružnice.", "Kinematika", "Kružno kretanje", HARD),
        Question("Period je vreme potrebno za:", listOf("jedan obilazak", "dva obilaska", "promenu brzine za 1 m/s", "zaustavljanje"), 0, "Period T je vreme jednog punog ciklusa ili obilaska.", "Kinematika", "Period", HARD),
        Question("Frekvencija 5 Hz znači:", listOf("jedan obrt za 5 s", "pet ciklusa u sekundi", "5 m/s", "period 5 s"), 1, "Herc znači jedan ciklus u sekundi; 5 Hz je 5 ciklusa/s.", "Kinematika", "Frekvencija", HARD),
        Question("Ako se period kružnog kretanja udvostruči, frekvencija se:", listOf("udvostruči", "prepolovi", "ne menja", "učetvorostruči"), 1, "f = 1/T, pa udvostručenje perioda prepolovljuje frekvenciju.", "Kinematika", "Period i frekvencija", HARD),
        Question("Prvi Njutnov zakon opisuje:", listOf("inerciju", "gravitaciju", "rad", "energiju"), 0, "Prvi Njutnov zakon je zakon inercije.", "Dinamika translacionog kretanja", "Njutnovi zakoni", EASY),
        Question("Drugi Njutnov zakon zapisuje se kao:", listOf("F = ma", "F = mv", "F = mgh", "F = A/t"), 0, "Rezultujuća sila jednaka je proizvodu mase i ubrzanja.", "Dinamika translacionog kretanja", "Njutnovi zakoni", EASY),
        Question("Na telo mase 4 kg deluje rezultujuća sila 12 N. Ubrzanje je:", listOf("2 m/s²", "3 m/s²", "4 m/s²", "48 m/s²"), 1, "a = F/m = 12/4 = 3 m/s².", "Dinamika translacionog kretanja", "Drugi Njutnov zakon", EASY),
        Question("Ako je rezultanta svih sila na telo jednaka nuli, ubrzanje je:", listOf("0", "g", "1 m/s²", "zavisi od mase"), 0, "Iz ΣF = ma sledi a = 0 kada je ΣF = 0.", "Dinamika translacionog kretanja", "Ravnoteža sila", EASY),
        Question("Ako je ΣF=0, telo:", listOf("mora da miruje", "može da miruje ili da se kreće konstantnom brzinom", "mora da ubrzava", "mora da se kreće kružno"), 1, "Telo zadržava stanje mirovanja ili ravnomernog pravolinijskog kretanja.", "Dinamika translacionog kretanja", "Inercija", EASY),
        Question("Treći Njutnov zakon kaže da sile akcije i reakcije imaju:", listOf("jednake intenzitete i suprotne smerove", "različite intenzitete", "isti smer", "istu napadnu tačku"), 0, "Akcija i reakcija su jednake po intenzitetu i suprotne po smeru.", "Dinamika translacionog kretanja", "Treći Njutnov zakon", EASY),
        Question("Sile akcije i reakcije deluju:", listOf("na isto telo", "na različita tela", "samo pri mirovanju", "samo gravitaciono"), 1, "Par akcija–reakcija uvek deluje na dva različita tela.", "Dinamika translacionog kretanja", "Treći Njutnov zakon", EASY),
        Question("Sila trenja klizanja približno je:", listOf("μ/m", "μN", "mg/μ", "ma²"), 1, "Za klizanje se koristi Ftr = μN.", "Dinamika translacionog kretanja", "Trenje", EASY),
        Question("Sila teže na telo mase 3 kg za g≈10 m/s² iznosi:", listOf("3 N", "10 N", "30 N", "300 N"), 2, "Fg = mg = 3·10 = 30 N.", "Dinamika translacionog kretanja", "Težina", EASY),
        Question("Za m=5 kg, μ=0,2 i g≈10 m/s², sila trenja na horizontalnoj podlozi je:", listOf("2 N", "5 N", "10 N", "50 N"), 2, "N=mg=50 N, pa je Ftr=μN=0,2·50=10 N.", "Dinamika translacionog kretanja", "Trenje", HARD),
        Question("Normalna sila deluje:", listOf("duž površine", "normalno na dodirnu površinu", "uvek naniže", "u smeru brzine"), 1, "Normalna sila je upravna na dodirnu površinu.", "Dinamika translacionog kretanja", "Normalna sila", HARD),
        Question("Na telo mase 2 kg deluju 10 N udesno i 4 N ulevo. Ubrzanje je:", listOf("2 m/s² udesno", "3 m/s² udesno", "6 m/s² udesno", "7 m/s² udesno"), 1, "Rezultanta je 6 N udesno, pa je a=6/2=3 m/s².", "Dinamika translacionog kretanja", "Rezultanta sila", HARD),
        Question("Masa je mera:", listOf("brzine", "inertnosti tela", "energije", "zapremine"), 1, "Što je masa veća, teže je promeniti stanje kretanja tela.", "Dinamika translacionog kretanja", "Inertnost", HARD),
        Question("Centripetalna sila je usmerena:", listOf("ka centru", "od centra", "tangentno", "suprotno gravitaciji"), 0, "Rezultujuća sila pri kružnom kretanju usmerena je ka centru.", "Dinamika translacionog kretanja", "Centripetalna sila", HARD),
        Question("Ako se pri istom poluprečniku brzina udvostruči, centripetalna sila postaje:", listOf("2 puta veća", "4 puta veća", "2 puta manja", "ista"), 1, "Fc = mv²/r, pa sila zavisi od kvadrata brzine.", "Dinamika translacionog kretanja", "Centripetalna sila", HARD),
        Question("Inercijalni referentni sistem je sistem u kojem važi:", listOf("prvi Njutnov zakon", "samo zakon gravitacije", "zakon prelamanja", "Arhimedov zakon"), 0, "Inercijalni sistem je onaj u kome važi zakon inercije.", "Dinamika translacionog kretanja", "Referentni sistemi", HARD),
        Question("Putnik pri naglom kočenju autobusa naginje se napred zbog:", listOf("trenja", "inercije", "gravitacije", "uzgona"), 1, "Telo teži da zadrži dotadašnju brzinu.", "Dinamika translacionog kretanja", "Inercija", HARD),
        Question("Ako ista rezultujuća sila deluje na dvostruko veću masu, ubrzanje je:", listOf("2 puta veće", "2 puta manje", "isto", "4 puta veće"), 1, "a=F/m, pa dvostruko veća masa daje dvostruko manje ubrzanje.", "Dinamika translacionog kretanja", "Drugi Njutnov zakon", HARD),
        Question("Moment sile zavisi od sile i:", listOf("kraka sile", "mase", "brzine", "vremena"), 0, "Intenzitet momenta sile je M = Fr, gde je r krak sile.", "Dinamika rotacionog kretanja", "Moment sile", EASY),
        Question("SI jedinica momenta sile je:", listOf("N", "J/s", "N·m", "kg/m"), 2, "Moment sile meri se u njutn-metrima.", "Dinamika rotacionog kretanja", "Moment sile", EASY),
        Question("Sila 20 N na kraku 0,5 m stvara moment:", listOf("5 N·m", "10 N·m", "20 N·m", "40 N·m"), 1, "M = Fr = 20·0,5 = 10 N·m.", "Dinamika rotacionog kretanja", "Moment sile", EASY),
        Question("Moment inercije u rotaciji ima ulogu analognu:", listOf("brzini", "masi u translaciji", "sili", "radu"), 1, "Moment inercije opisuje otpor promeni rotacionog kretanja.", "Dinamika rotacionog kretanja", "Moment inercije", EASY),
        Question("Ugaona brzina najčešće se označava sa:", listOf("α", "ω", "F", "p"), 1, "Standardna oznaka za ugaonu brzinu je ω.", "Dinamika rotacionog kretanja", "Ugaona brzina", EASY),
        Question("Ugaono ubrzanje najčešće se označava sa:", listOf("α", "ω", "M", "I"), 0, "Standardna oznaka za ugaono ubrzanje je α.", "Dinamika rotacionog kretanja", "Ugaono ubrzanje", EASY),
        Question("Veza analogna F=ma kod rotacije je:", listOf("M=I/α", "M=Iα", "M=ωt", "M=mv"), 1, "Rezultujući moment sile jednak je proizvodu momenta inercije i ugaonog ubrzanja.", "Dinamika rotacionog kretanja", "Dinamika rotacije", HARD),
        Question("Ako isti moment sile deluje na telo sa većim momentom inercije, ugaono ubrzanje je:", listOf("veće", "manje", "isto", "uvek nula"), 1, "Iz α=M/I sledi da veći I daje manje α.", "Dinamika rotacionog kretanja", "Dinamika rotacije", HARD),
        Question("Moment impulsa krutog tela oko fiksne ose može se izraziti kao:", listOf("L=Iω", "L=ma", "L=Fs", "L=mgh"), 0, "Za rotaciju krutog tela oko fiksne ose važi L=Iω.", "Dinamika rotacionog kretanja", "Moment impulsa", HARD),
        Question("Spreg sila čine dve paralelne sile:", listOf("istog smera", "jednakog intenziteta i suprotnih smerova", "različitih intenziteta", "iste napadne linije"), 1, "Spreg čine dve jednake, paralelne i suprotno usmerene sile različitih napadnih linija.", "Dinamika rotacionog kretanja", "Spreg sila", HARD),
        Question("Ako se ista sila primeni dalje od ose rotacije, njen moment se:", listOf("povećava", "smanjuje", "ne menja", "poništava"), 0, "M=Fr, pa veći krak r daje veći moment.", "Dinamika rotacionog kretanja", "Moment sile", HARD),
        Question("Vrata je najlakše otvoriti gurajući:", listOf("blizu šarki", "što dalje od šarki", "tačno na šarku", "položaj nije bitan"), 1, "Što je veći krak sile, veći je moment i lakše je rotirati vrata.", "Dinamika rotacionog kretanja", "Moment sile", HARD),
        Question("Uslov translacione ravnoteže je:", listOf("ΣF=0", "ΣF=ma≠0", "ΣM≠0", "v=0 obavezno"), 0, "Za translacionu ravnotežu vektorski zbir svih sila mora biti nula.", "Ravnoteža tela", "Translaciona ravnoteža", EASY),
        Question("Uslov rotacione ravnoteže je:", listOf("ΣF=ma", "ΣM=0", "ω≠0", "M=Iω"), 1, "Za rotacionu ravnotežu zbir momenata sila mora biti nula.", "Ravnoteža tela", "Rotaciona ravnoteža", EASY),
        Question("Za potpunu ravnotežu krutog tela potrebno je:", listOf("samo ΣF=0", "samo ΣM=0", "ΣF=0 i ΣM=0", "samo v=0"), 2, "Potrebna je i translaciona i rotaciona ravnoteža.", "Ravnoteža tela", "Ravnoteža krutog tela", EASY),
        Question("Telo je u stabilnoj ravnoteži ako se nakon malog pomeranja:", listOf("vraća u početni položaj", "udaljava od početnog položaja", "ostaje u novom položaju", "ubrzava stalno"), 0, "Kod stabilne ravnoteže postoji tendencija povratka u prvobitni položaj.", "Ravnoteža tela", "Vrste ravnoteže", EASY),
        Question("Kuglica na vrhu ispupčene površine je primer:", listOf("stabilne ravnoteže", "labilne ravnoteže", "indiferentne ravnoteže", "dinamičke ravnoteže"), 1, "Malo pomeranje udaljava kuglicu od početnog položaja.", "Ravnoteža tela", "Vrste ravnoteže", EASY),
        Question("Kugla na horizontalnoj ravni približan je primer:", listOf("labilne ravnoteže", "stabilne ravnoteže", "indiferentne ravnoteže", "nemoguće ravnoteže"), 2, "Posle malog pomeranja kugla ostaje u novom položaju iste potencijalne energije.", "Ravnoteža tela", "Vrste ravnoteže", HARD),
        Question("Poluga je u ravnoteži kada su momenti sila:", listOf("različiti", "jednaki po intenzitetu i suprotni po smeru rotacije", "oba nužno nula", "proporcionalni masama"), 1, "Za ravnotežu zbir momenata mora biti nula.", "Ravnoteža tela", "Poluga", HARD),
        Question("Na jednom kraku poluge od 2 m deluje sila 10 N. Za ravnotežu, sila na kraku 1 m treba da bude:", listOf("5 N", "10 N", "20 N", "40 N"), 2, "10·2 = F·1, pa je F=20 N.", "Ravnoteža tela", "Poluga", HARD),
        Question("Idealna strma ravan omogućava podizanje tereta uz:", listOf("manju silu na dužem putu", "veću silu i kraći put", "rad jednak nuli", "odsustvo gravitacije"), 0, "Idealna mašina menja odnos sile i puta, ali ne smanjuje potreban rad.", "Ravnoteža tela", "Strma ravan", HARD),
        Question("Niže težište tela uglavnom doprinosi:", listOf("većoj stabilnosti", "manjoj stabilnosti", "većoj masi", "većoj brzini"), 0, "Niže težište otežava prevrtanje i povećava stabilnost.", "Ravnoteža tela", "Stabilnost", HARD),
        Question("Njutnova gravitaciona sila proporcionalna je:", listOf("proizvodu masa", "zbiru masa", "razlici masa", "zapremini"), 0, "F=Gm₁m₂/r², pa je sila proporcionalna proizvodu masa.", "Gravitacija", "Zakon gravitacije", EASY),
        Question("Gravitaciona sila je obrnuto proporcionalna:", listOf("r", "r²", "√r", "masi"), 1, "Njutnov zakon gravitacije sadrži faktor 1/r².", "Gravitacija", "Zakon gravitacije", EASY),
        Question("Ako se rastojanje između dve mase udvostruči, gravitaciona sila postaje:", listOf("2 puta manja", "4 puta manja", "4 puta veća", "ista"), 1, "Zbog zavisnosti 1/r², udvostručenje r smanjuje silu četiri puta.", "Gravitacija", "Zakon gravitacije", EASY),
        Question("Ako se jedna od dve mase udvostruči, a ostalo ostane isto, gravitaciona sila je:", listOf("dvostruko veća", "četiri puta veća", "dvostruko manja", "ista"), 0, "Sila je direktno proporcionalna svakoj od masa.", "Gravitacija", "Zakon gravitacije", EASY),
        Question("Jačina gravitacionog polja ima jedinicu:", listOf("N/kg", "J", "W", "kg/N"), 0, "Jačina polja je sila po jedinici mase, pa je jedinica N/kg.", "Gravitacija", "Gravitaciono polje", EASY),
        Question("Blizu Zemljine površine g je približno:", listOf("1 m/s²", "9,81 m/s²", "98,1 m/s²", "0 m/s²"), 1, "Standardna vrednost ubrzanja Zemljine teže je približno 9,81 m/s².", "Gravitacija", "Gravitaciono polje", HARD),
        Question("Težina tela je sila kojom telo, usled gravitacije, deluje na:", listOf("podlogu ili vešanje", "samo Zemlju", "sopstveni centar mase", "vazduh"), 0, "Težina je sila delovanja tela na oslonac ili vešanje usled gravitacije.", "Gravitacija", "Težina", HARD),
        Question("Astronaut u orbiti deluje bestežinski zato što:", listOf("nema gravitacije", "astronaut i letelica zajedno slobodno padaju", "astronaut nema masu", "Zemlja ne privlači satelite"), 1, "U orbiti i astronaut i letelica imaju isto gravitaciono ubrzanje i nalaze se u slobodnom padu.", "Gravitacija", "Bestežinsko stanje", HARD),
        Question("Gravitaciona sila između dva tela je:", listOf("odbojna", "privlačna", "nekad privlačna, nekad odbojna", "uvek nula"), 1, "Njutnova gravitaciona sila između masa je privlačna.", "Gravitacija", "Zakon gravitacije", HARD),
        Question("Satelit ostaje u orbiti jer gravitacija ima ulogu:", listOf("sile trenja", "centripetalne sile", "elastične sile", "sile potiska"), 1, "Gravitaciona sila obezbeđuje centripetalno ubrzanje satelita.", "Gravitacija", "Sateliti", HARD),
        Question("Impuls tela je:", listOf("p=mv", "p=ma", "p=mgh", "p=Fs"), 0, "Impuls je proizvod mase i brzine: p=mv.", "Zakoni održanja", "Impuls", EASY),
        Question("SI jedinica impulsa je:", listOf("J", "kg·m/s", "W", "N/m"), 1, "Iz p=mv sledi jedinica kg·m/s.", "Zakoni održanja", "Impuls", EASY),
        Question("Telo mase 2 kg pri brzini 5 m/s ima impuls:", listOf("2,5 kg·m/s", "7 kg·m/s", "10 kg·m/s", "25 kg·m/s"), 2, "p=mv=2·5=10 kg·m/s.", "Zakoni održanja", "Impuls", EASY),
        Question("Ukupan impuls izolovanog sistema:", listOf("raste", "opada", "ostaje konstantan", "uvek je nula"), 2, "U izolovanom sistemu ukupan impuls se održava.", "Zakoni održanja", "Održanje impulsa", EASY),
        Question("Telo 2 kg pri 3 m/s zalepi se za telo 1 kg u mirovanju. Zajednička brzina je:", listOf("1 m/s", "2 m/s", "3 m/s", "6 m/s"), 1, "2·3=(2+1)v, pa je v=2 m/s.", "Zakoni održanja", "Neelastični sudar", EASY),
        Question("Mehanički rad konstantne sile paralelne pomeranju je:", listOf("A=Fs", "A=F/s", "A=Ft", "A=mv"), 0, "Za silu paralelnu pomeranju važi A=Fs.", "Zakoni održanja", "Rad", EASY),
        Question("SI jedinica rada je:", listOf("N", "J", "W", "Pa"), 1, "Rad i energija mere se u džulima (J).", "Zakoni održanja", "Rad", EASY),
        Question("Sila 10 N pomera telo 4 m u svom smeru. Rad je:", listOf("2,5 J", "14 J", "40 J", "400 J"), 2, "A=Fs=10·4=40 J.", "Zakoni održanja", "Rad", EASY),
        Question("Kada je sila normalna na pomeraj, njen rad je:", listOf("0", "Fs", "maksimalan", "uvek negativan"), 0, "A=Fs cos 90°=0.", "Zakoni održanja", "Rad", EASY),
        Question("Kinetička energija tela je:", listOf("mv", "mv²/2", "mgh", "Fs"), 1, "Ek=mv²/2.", "Zakoni održanja", "Kinetička energija", EASY),
        Question("Telo mase 2 kg pri brzini 4 m/s ima kinetičku energiju:", listOf("4 J", "8 J", "16 J", "32 J"), 2, "Ek=2·4²/2=16 J.", "Zakoni održanja", "Kinetička energija", HARD),
        Question("Gravitaciona potencijalna energija blizu površine Zemlje je:", listOf("mgh", "mv²/2", "ma", "Pt"), 0, "Ep=mgh.", "Zakoni održanja", "Potencijalna energija", HARD),
        Question("Telo mase 2 kg na visini 5 m, za g≈10 m/s², ima potencijalnu energiju:", listOf("10 J", "25 J", "50 J", "100 J"), 3, "Ep=mgh=2·10·5=100 J.", "Zakoni održanja", "Potencijalna energija", HARD),
        Question("Bez trenja ukupna mehanička energija sistema:", listOf("raste", "opada", "održava se", "postaje nula"), 2, "Kada nema disipativnih sila, zbir kinetičke i potencijalne energije ostaje konstantan.", "Zakoni održanja", "Mehanička energija", HARD),
        Question("Pri slobodnom padu bez otpora vazduha:", listOf("potencijalna energija prelazi u kinetičku", "kinetička energija nestaje", "obe energije rastu", "mehanička energija se ne održava"), 0, "Kako telo pada, Ep opada, a Ek raste, uz očuvanje ukupne mehaničke energije.", "Zakoni održanja", "Mehanička energija", HARD),
        Question("Snaga je:", listOf("P=A/t", "P=At", "P=F/t", "P=mv"), 0, "Snaga je rad izvršen u jedinici vremena.", "Zakoni održanja", "Snaga", HARD),
        Question("Mašina izvrši 1000 J rada za 5 s. Snaga je:", listOf("50 W", "100 W", "200 W", "5000 W"), 2, "P=A/t=1000/5=200 W.", "Zakoni održanja", "Snaga", HARD),
        Question("U potpuno neelastičnom sudaru održava se:", listOf("impuls, ali ne i kinetička energija", "samo kinetička energija", "ni impuls ni energija", "brzina svakog tela"), 0, "Ukupan impuls izolovanog sistema se održava, dok se deo kinetičke energije pretvara u druge oblike.", "Zakoni održanja", "Sudari", HARD),
        Question("Klizač privuče ruke uz telo tokom okretanja i počinje brže da se okreće zbog održanja:", listOf("energije mirovanja", "sile", "momenta impulsa", "mase"), 2, "Smanjenjem momenta inercije, ugaona brzina raste da bi L=Iω ostao konstantan.", "Zakoni održanja", "Moment impulsa", HARD),
        Question("Ako na sistem ne deluje spoljašnji moment sile, ukupan moment impulsa sistema:", listOf("raste", "opada", "ostaje konstantan", "mora biti nula"), 2, "Bez spoljašnjeg momenta sile moment impulsa se održava.", "Zakoni održanja", "Moment impulsa", HARD),
    )

    fun easyQuestions(): List<Question> = buildTest(EASY)
    fun hardQuestions(): List<Question> = buildTest(HARD)

    private fun buildTest(difficulty: String): List<Question> {
        val all = allQuestions()
        val selected = mutableListOf<Question>()
        selected += all.slice(if (difficulty == EASY) 0..3 else 4..7).shuffled().take(2)
        selected += all.slice(if (difficulty == EASY) 8..18 else 19..29).shuffled().take(4)
        selected += all.slice(if (difficulty == EASY) 30..38 else 39..47).shuffled().take(4)
        selected += all.slice(if (difficulty == EASY) 48..53 else 54..59).shuffled().take(2)
        selected += all.slice(if (difficulty == EASY) 60..64 else 65..69).shuffled().take(2)
        selected += all.slice(if (difficulty == EASY) 70..74 else 75..79).shuffled().take(2)
        selected += all.slice(if (difficulty == EASY) 80..89 else 90..99).shuffled().take(4)
        return selected.shuffled()
    }
}
