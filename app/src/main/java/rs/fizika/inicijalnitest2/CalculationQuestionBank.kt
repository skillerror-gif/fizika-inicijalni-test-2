package rs.fizika.inicijalnitest2

object CalculationQuestionBank {
    // Originalni računski zadaci, organizovani prema sedam oblasti udžbenika Zavoda.
    // Po oblasti: 5 osnovnih + 5 srednjih = 70 zadataka.
    private fun q(text:String, a:String, b:String, c:String, d:String, correct:Int, explanation:String) =
        Question(text, listOf(a,b,c,d), correct, explanation)

    fun basic(): List<Question> = listOf(
        // UVOD (5)
        q("Brzina automobila je 72 km/h. Kolika je u m/s?","10 m/s","20 m/s","25 m/s","36 m/s",1,"72/3,6 = 20 m/s."),
        q("Dve sile istog smera imaju 4 N i 7 N. Kolika je rezultanta?","3 N","11 N","28 N","7 N",1,"Za isti smer intenziteti se sabiraju: 4+7=11 N."),
        q("Sile 9 N udesno i 4 N ulevo deluju duž iste prave. Rezultanta je:","5 N udesno","13 N udesno","5 N ulevo","36 N",0,"9-4=5 N, u smeru veće sile."),
        q("Vektori intenziteta 6 i 8 su međusobno normalni. Intenzitet zbira je:","2","7","10","14",2,"Pitagorina teorema: √(6²+8²)=10."),
        q("Dužina 2,5 km izražena u metrima je:","25 m","250 m","2500 m","25000 m",2,"1 km=1000 m, pa je 2,5 km=2500 m."),
        // KINEMATIKA (5)
        q("Biciklista pređe 180 m za 30 s. Srednja brzina je:","3 m/s","6 m/s","9 m/s","15 m/s",1,"v=s/t=180/30=6 m/s."),
        q("Telo kreće iz mirovanja ubrzanjem 2 m/s². Brzina posle 5 s je:","5 m/s","7 m/s","10 m/s","20 m/s",2,"v=at=2·5=10 m/s."),
        q("Brzina se poveća sa 4 na 16 m/s za 6 s. Ubrzanje je:","2 m/s²","3 m/s²","6 m/s²","12 m/s²",0,"a=(16-4)/6=2 m/s²."),
        q("Telo se kreće ravnomerno 8 m/s tokom 15 s. Pređeni put je:","23 m","120 m","80 m","7,5 m",1,"s=vt=8·15=120 m."),
        q("Telo slobodno pada 2 s. Za g=10 m/s² njegova brzina je:","5 m/s","10 m/s","20 m/s","40 m/s",2,"v=gt=20 m/s."),
        // DINAMIKA TRANSLACIJE (5)
        q("Na telo mase 6 kg deluje rezultujuća sila 18 N. Ubrzanje je:","2 m/s²","3 m/s²","6 m/s²","108 m/s²",1,"a=F/m=18/6=3 m/s²."),
        q("Kolika sila daje telu mase 4 kg ubrzanje 2,5 m/s²?","1,6 N","6,5 N","10 N","16 N",2,"F=ma=4·2,5=10 N."),
        q("Kolika je sila teže tela mase 7 kg za g=10 m/s²?","7 N","17 N","70 N","700 N",2,"Fg=mg=70 N."),
        q("Na horizontalnoj podlozi m=5 kg, μ=0,2, g=10 m/s². Trenje je:","5 N","10 N","20 N","50 N",1,"N=mg=50 N; Ftr=μN=10 N."),
        q("Telo mase 2 kg vuče sila 14 N, a trenje je 4 N. Ubrzanje je:","2 m/s²","5 m/s²","7 m/s²","9 m/s²",1,"Frez=14-4=10 N; a=10/2=5 m/s²."),
        // ROTACIJA (5)
        q("Telo napravi 20 obrtaja za 10 s. Frekvencija je:","0,5 Hz","2 Hz","10 Hz","200 Hz",1,"f=N/t=20/10=2 Hz."),
        q("Frekvencija je 4 Hz. Period je:","0,25 s","2 s","4 s","8 s",0,"T=1/f=0,25 s."),
        q("Tačka se kreće po kružnici poluprečnika 2 m brzinom 6 m/s. Centripetalno ubrzanje je:","3 m/s²","12 m/s²","18 m/s²","36 m/s²",2,"ac=v²/r=36/2=18 m/s²."),
        q("Sila 10 N deluje normalno na krak 0,5 m. Moment sile je:","5 N·m","10 N·m","20 N·m","0,05 N·m",0,"M=Fr=10·0,5=5 N·m."),
        q("Točak napravi 6 obrtaja za 3 s. Period je:","0,5 s","2 s","3 s","18 s",0,"f=2 Hz, pa je T=1/f=0,5 s."),
        // RAVNOTEŽA (5)
        q("Na polugu deluje sila 20 N na kraku 0,4 m. Moment je:","8 N·m","20 N·m","50 N·m","0,02 N·m",0,"M=Fr=20·0,4=8 N·m."),
        q("Za ravnotežu poluge sila 30 N deluje na 0,2 m. Kolika sila na 0,6 m daje isti moment?","5 N","10 N","30 N","90 N",1,"30·0,2=F·0,6, pa je F=10 N."),
        q("Telo mase 4 kg miruje na horizontalnoj podlozi. Za g=10 m/s² normalna sila je:","4 N","10 N","40 N","400 N",2,"U ravnoteži N=mg=40 N."),
        q("Sile 12 N i 12 N deluju suprotno duž iste prave. Rezultanta je:","0 N","12 N","24 N","144 N",0,"Jednake su i suprotne, pa je rezultanta nula."),
        q("Teret 100 N visi na jednoj vertikalnoj niti i miruje. Zatezna sila niti je:","0 N","50 N","100 N","200 N",2,"U ravnoteži zatezna sila jednaka je težini: 100 N."),
        // GRAVITACIJA (5)
        q("Kolika je težina tela mase 12 kg blizu Zemlje za g=10 m/s²?","12 N","22 N","120 N","1200 N",2,"Fg=mg=120 N."),
        q("Telo težine 250 N ima masu približno (g=10 m/s²):","2,5 kg","25 kg","250 kg","2500 kg",1,"m=Fg/g=250/10=25 kg."),
        q("Ako je masa tela 3 kg, njegova potencijalna energija na 5 m za g=10 m/s² je:","15 J","50 J","150 J","300 J",2,"Ep=mgh=3·10·5=150 J."),
        q("Telo slobodno pada sa mirovanja 3 s, g=10 m/s². Brzina je:","10 m/s","20 m/s","30 m/s","90 m/s",2,"v=gt=30 m/s."),
        q("Koliki put pređe telo u slobodnom padu za 2 s, g=10 m/s²?","10 m","20 m","40 m","80 m",1,"s=gt²/2=10·4/2=20 m."),
        // ZAKONI ODRŽANJA (5)
        q("Sila 20 N pomeri telo 3 m u svom smeru. Rad je:","6 J","17 J","60 J","600 J",2,"A=Fs=20·3=60 J."),
        q("Rad 600 J izvrši se za 20 s. Snaga je:","20 W","30 W","120 W","12000 W",1,"P=A/t=600/20=30 W."),
        q("Kinetička energija tela mase 2 kg pri 4 m/s je:","8 J","16 J","32 J","4 J",1,"Ek=mv²/2=2·16/2=16 J."),
        q("Potencijalna energija tela mase 5 kg na visini 2 m, g=10 m/s² je:","10 J","25 J","50 J","100 J",3,"Ep=mgh=5·10·2=100 J."),
        q("Telo mase 3 kg kreće se 4 m/s. Količina kretanja je:","0,75 kg·m/s","7 kg·m/s","12 kg·m/s","24 kg·m/s",2,"p=mv=3·4=12 kg·m/s.")
    )

    fun intermediate(): List<Question> = listOf(
        // UVOD (5)
        q("Vektori 5 N i 12 N su normalni. Intenzitet rezultante je:","7 N","13 N","17 N","60 N",1,"R=√(5²+12²)=13 N."),
        q("Brzina 90 km/h traje 40 s. Koliki put se pređe?","360 m","1000 m","2500 m","3600 m",1,"90 km/h=25 m/s; s=25·40=1000 m."),
        q("Sile 14 N udesno, 9 N ulevo i 3 N ulevo deluju duž iste prave. Rezultanta je:","2 N udesno","8 N udesno","20 N ulevo","26 N udesno",0,"14-9-3=2 N udesno."),
        q("Dve normalne komponente vektora su 9 i 12. Intenzitet vektora je:","3","15","21","108",1,"√(9²+12²)=15."),
        q("Rezultat merenja je 2,40 m, a apsolutna greška 0,02 m. Relativna greška je približno:","0,08%","0,83%","8,3%","12%",1,"0,02/2,40·100%≈0,83%."),
        // KINEMATIKA (5)
        q("Telo kreće iz mirovanja sa a=3 m/s². Koliki put pređe za 4 s?","12 m","18 m","24 m","48 m",2,"s=at²/2=3·16/2=24 m."),
        q("Automobil brzine 20 m/s koči ubrzanjem -4 m/s². Zaustavni put je:","20 m","40 m","50 m","80 m",2,"0=v0²+2as; s=400/8=50 m."),
        q("Telo je bačeno vertikalno naviše brzinom 30 m/s. Za g=10 m/s² vreme penjanja je:","1 s","2 s","3 s","6 s",2,"0=v0-gt; t=30/10=3 s."),
        q("Voz pređe prvu polovinu puta brzinom 10 m/s, drugu 20 m/s. Srednja brzina je:","12 m/s","13,3 m/s","15 m/s","16,7 m/s",1,"Za jednake puteve vsr=2v1v2/(v1+v2)=400/30≈13,3 m/s."),
        q("Kružnica ima r=2 m, a telo brzinu 4 m/s. Period je približno (π≈3,14):","1,57 s","3,14 s","6,28 s","12,56 s",1,"T=2πr/v=4π/4=π≈3,14 s."),
        // DINAMIKA (5)
        q("Telo mase 10 kg vuče horizontalna sila 50 N. μ=0,2, g=10. Ubrzanje je:","1 m/s²","2 m/s²","3 m/s²","5 m/s²",2,"Ftr=20 N; Frez=30 N; a=3 m/s²."),
        q("Telo mase 5 kg na glatkoj podlozi trpe sile 30 N desno i 10 N levo. Ubrzanje je:","2 m/s²","4 m/s²","6 m/s²","8 m/s²",1,"Frez=20 N; a=20/5=4 m/s²."),
        q("Telo mase 4 kg klizi niz glatku ravan nagiba 30°. Za g=10 m/s² komponenta težine niz ravan je:","10 N","20 N","34,6 N","40 N",1,"mg sin30°=40·0,5=20 N."),
        q("Lift ubrzava naviše 2 m/s². Osoba mase 60 kg ima prividnu težinu (g=10):","480 N","600 N","720 N","1200 N",2,"N=m(g+a)=60·12=720 N."),
        q("Telo mase 2 kg kruži brzinom 6 m/s po r=3 m. Centripetalna sila je:","12 N","18 N","24 N","36 N",2,"Fc=mv²/r=2·36/3=24 N."),
        // ROTACIJA (5)
        q("Disk se obrće frekvencijom 5 Hz. Ugaona brzina je približno:","5 rad/s","10 rad/s","15,7 rad/s","31,4 rad/s",3,"ω=2πf≈31,4 rad/s."),
        q("Tačka na obodu točka r=0,4 m ima ω=10 rad/s. Linearna brzina je:","2,5 m/s","4 m/s","10,4 m/s","25 m/s",1,"v=ωr=10·0,4=4 m/s."),
        q("Sila 30 N deluje normalno na krak 0,25 m. Moment je:","7,5 N·m","12 N·m","30 N·m","120 N·m",0,"M=Fr=7,5 N·m."),
        q("Telo r=0,5 m kruži sa ω=4 rad/s. Centripetalno ubrzanje je:","2 m/s²","4 m/s²","8 m/s²","16 m/s²",2,"ac=ω²r=16·0,5=8 m/s²."),
        q("Točak iz mirovanja dostigne ω=12 rad/s za 3 s. Ugaono ubrzanje je:","3 rad/s²","4 rad/s²","9 rad/s²","36 rad/s²",1,"α=Δω/Δt=12/3=4 rad/s²."),
        // RAVNOTEŽA (5)
        q("Na poluzi su 40 N na 0,3 m i sila F na suprotnoj strani na 0,8 m. Za ravnotežu F je:","10 N","15 N","30 N","106,7 N",1,"40·0,3=F·0,8; F=15 N."),
        q("Greda težine 200 N oslonjena je u sredini. Teret 100 N je 1 m levo. Na kojoj udaljenosti desno sila 50 N daje ravnotežu?","0,5 m","1 m","2 m","4 m",2,"100·1=50·x, pa x=2 m."),
        q("Telo mase 8 kg miruje na ravni nagiba 30° bez trenja, zadržano užetom duž ravni. Zatezna sila je:","20 N","40 N","69,3 N","80 N",1,"T=mg sin30°=80·0,5=40 N."),
        q("Dve paralelne sile 10 N i 30 N istog smera imaju rezultantu:","20 N","30 N","40 N","300 N",2,"Rezultanta paralelnih sila istog smera je zbir: 40 N."),
        q("Moment sile je 18 N·m, a krak 0,6 m. Sila normalna na krak je:","10,8 N","18,6 N","30 N","108 N",2,"F=M/r=18/0,6=30 N."),
        // GRAVITACIJA (5)
        q("Na visini gde je g=8 m/s² telo mase 5 kg ima težinu:","13 N","40 N","50 N","80 N",1,"Fg=mg=5·8=40 N."),
        q("Ako se rastojanje između dve mase udvostruči, gravitaciona sila postaje:","2 puta manja","4 puta manja","2 puta veća","4 puta veća",1,"F je obrnuto proporcionalna r²."),
        q("Telo padne sa visine 45 m, g=10 m/s². Vreme pada je:","1,5 s","3 s","4,5 s","9 s",1,"h=gt²/2; 45=5t²; t=3 s."),
        q("Telo je bačeno naviše 20 m/s. Maksimalna visina iznad mesta izbačaja je (g=10):","10 m","20 m","40 m","200 m",1,"h=v0²/(2g)=400/20=20 m."),
        q("Telo mase 2 kg spusti se za 15 m. Promena gravitacione potencijalne energije po iznosu je (g=10):","30 J","150 J","300 J","600 J",2,"|ΔEp|=mgh=2·10·15=300 J."),
        // ZAKONI ODRŽANJA (5)
        q("Telo mase 4 kg ubrza sa 2 na 6 m/s. Promena kinetičke energije je:","32 J","64 J","72 J","128 J",1,"ΔEk=m(v²-v0²)/2=2·(36-4)=64 J."),
        q("Mašina obavi 24 kJ rada za 2 min. Snaga je:","12 W","120 W","200 W","480 W",2,"P=24000/120=200 W."),
        q("Telo mase 2 kg pada bez otpora sa visine 20 m. Brzina neposredno pre tla je (g=10):","10 m/s","14,1 m/s","20 m/s","40 m/s",2,"mgh=mv²/2; v=√(2gh)=20 m/s."),
        q("Kolica mase 2 kg brzine 6 m/s sudare se i spoje sa mirnim kolicima mase 4 kg. Zajednička brzina je:","1 m/s","2 m/s","3 m/s","6 m/s",1,"Održanje impulsa: v=2·6/(2+4)=2 m/s."),
        q("Sila 50 N deluje pod uglom 60° prema pomeraju 4 m. Rad je:","50 J","100 J","173 J","200 J",1,"A=Fs cos60°=50·4·0,5=100 J.")
    )

    fun randomSix(): List<Question> {
        // 3 osnovna + 3 srednja, nasumično; oblasti se menjaju od testa do testa.
        return (basic().shuffled().take(3) + intermediate().shuffled().take(3)).shuffled()
    }
}
