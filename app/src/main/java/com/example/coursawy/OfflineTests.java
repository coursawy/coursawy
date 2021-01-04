package com.example.coursawy;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coursawy.model.Exam;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OfflineTests extends AppCompatActivity {
    @BindView(R.id.timer_tv)
    TextView timerTv;
    @BindView(R.id.exam_title_tv)
    TextView examTitleTv;
    @BindView(R.id.counter_tv)
    TextView counterTv;
    @BindView(R.id.info_v)
    View infoV;
    @BindView(R.id.mark_tv)
    TextView markTv;
    @BindView(R.id.equestion_tv)
    TextView equestionTv;
    @BindView(R.id.eradio_one)
    RadioButton eradioOne;
    @BindView(R.id.eradio_two)
    RadioButton eradioTwo;
    @BindView(R.id.eradio_three)
    RadioButton eradioThree;
    @BindView(R.id.eradio_four)
    RadioButton eradioFour;
    @BindView(R.id.eradio_group_all)
    RadioGroup eradioGroupAll;
    @BindView(R.id.exam_submit_btn)
    Button examSubmitBtn;
    @BindView(R.id.exam_ll)
    LinearLayout examLl;
    @BindView(R.id.show_marks_btn)
    Button showMarksBtn;
    @BindView(R.id.questions_ll)
    LinearLayout questionsLl;
    @BindView(R.id.back_iv)
    ImageView backIv;

    @BindView(R.id.back_btn)
    Button backBtn;
    @BindView(R.id.next_btn)
    Button nextBtn;
    @BindView(R.id.questions_progress)
    SeekBar questionsProgress;
    private String testId = "";

    private int testIndex;
    private String testName;
    private ArrayList<Exam> examList;
    String answersString = "";
    String marksString = "";
    String userId = "";
    public static String answers = "";
    //    FirebaseAuth mAuth;
//    FirebaseDatabase database;
//    DatabaseReference isSolvedRef;
//    DatabaseReference solvedMarks;
    SharedPreferences answersPreferences;
    public static int MR = 0, mark = 0;
    private int x = MR + 1;
    private int backFlag = 0;
    TextToSpeech textToSpeech;
    ArrayList<String> stuAnswersList;


    DatabaseReference examRef;
    FirebaseDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);
        ButterKnife.bind(this);
//        mAuth = FirebaseAuth.getInstance();
//        database = FirebaseDatabase.getInstance();
        answersPreferences = getPreferences(MODE_PRIVATE);
        stuAnswersList = new ArrayList<>();
        questionsProgress.setProgress(x);

//        timerTv.setText("لن تتمكن من العودة بعد حل اول سؤال");
        timerTv.setVisibility(View.GONE);
        testId = getIntent().getExtras().getString("id");
        testName = getIntent().getExtras().getString("unitName");
        answersString = answersPreferences.getString("answers" + testName, "MR");
        marksString = answersPreferences.getString("marks" + testName, "MR");

        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        examTitleTv.setText(testName);
//        if (mAuth.getCurrentUser() != null) {
//            userId = mAuth.getCurrentUser().getUid();
//            isSolvedRef = database.getReference("solvedOfflineAnswers/" + testName).child(userId);
//            solvedMarks = database.getReference("solvedOfflineMarks/" + testName).child(userId);
//        }
        examList = new ArrayList<>();
//            SharedPreferences.Editor editor = answersPreferences.edit();
//            editor.clear();
//            editor.apply();
//            editor.commit();
        if (getIntent().getExtras().getString("type").equals("offline"))
        {
            switch (testId) {
                case "ahmed2020":
                    examTitleTv.setText("الامتحان هدفه الوقوف على مستواك و معالجة أي سلبيات لذا كن قدر المسئولية يا فااااااشل");

                    String[] ras1 = {"b", "b", "c", "c", "d", "d", "a", "a", "c", "c", "b", "b", "b", "c", "c", "c", "d", "d", "a", "a", "b", "b", "b", "c", "c", "b", "b", "c", "c", "c", "a", "a", "c", "c", "b", "b", "a", "a", "d", "d", "d", "a", "b", "c", "a", "a", "c", "c", "a", "a", "b", "b", "c", "c", "d", "d", "a", "a", "b", "b", "d", "d", "c", "c", "c", "c", "a", "c", "c", "c", "d", "d", "b", "b", "b", "c", "c", "d", "d", "b", "b", "b", "d", "a", "a", "c", "b", "d", "b", "c", "d", "d", "c", "c", "d", "b", "c", "b"};
                    List<String> strings1 = Arrays.asList("1 My grandfather was a strong ................ in the importance of girls’ education.   ", "a  pioneer", "b  believer", "c  customer", "d  stylist   ", "2 He was ................ on getting his money by tomorrow.  ", "a  insist", "b  insistent", "c  insistence", "d  insistently", "3 The noise outside has made me ................ so I couldn't concentrate.  ", "a  confusion", "b  confusing", "c  confused", "d  confuse", "4 I really admire Charles Dickens as a novelist as his writing................ is unique.  ", "a  behaviour", "b  attitude", "c  style", "d  conduct", "5 Young people like to wear ................ clothes to follow the latest fashion.", "a  old-fashioned", "b  old", "c  unfashionable", "d  fashionable", "6 \"The Postman\" was ................ into a successful film.  ", "a  spread", "b  done", "c  written", "d  made", "7 As well as ................ an important writer, he was an expert on Arab culture.  ", "a  being", "b  to be", "c  be", "d  been ", "8 The astronauts went on ................ spacewalk to replace a broken part.", "a  a two-hour", "b  two-hour", "c  two-hours", "d  two-hour's", "9 His first novel................ him as one of the great short story writers of the Arab world. ", "a  refreshed", "b  published", "c  established", "d  furnished", "10 Scientists have a great ................ on our life.  ", "a  affect", "b  affective", "c  effect", "d  effective", "11 Everyone ................ mistakes when they’re learning something new.", "a  make", "b  makes", "c  do", "d  does", "12 We should ................ people’s privacy and avoid interfering in their affairs.", "a  control", "b  respect", "c  defame", "d  break", "13 They made him ................ the house as a form of punishment.", "a  to tidy", "b  tidy", "c  tidying", "d  be tidied", "14 Toka has a bad ................ of biting her nail when she’s nervous.", "a  fashion", "b  custom", "c  habit", "d  hobby", "15 It ................ me three hours to reach Italy.", "a  spent", "b  cost", "c  took", "d  worked", "16 There was an ................ article on vegetarianism in the paper yesterday. ", "a  excited", "b  interested", "c  interesting", "d  interest ", "17 She was ................ a medal for showing supreme bravery. ", "a  warded", "b  rewarded", "c  rewarding", "d  awarded ", "18 They have to provide a contract by ................ .", "a  raw", "b  war", "c  low", "d  law ", "19 The number of spectators at football matches was lower than................ last season. ", "a  average", "b  avenge ", "c  publisher", "d  revenge ", "20 What do you think the ................ is getting at in these lines in the second verse? ", "a  poet", "b  playwright", "c  journalist", "d  novelist ", "21 The patient was made................ in bed for three days.   ", "a  stay ", "b  to stay", "c  staying", "d  stayed", "22 An efficient teacher doesn’t silence the students but he ................ them.   ", "a  spoils", "b  disciplines", "c  honours", "d  punishes", "23 His parents do not let him ................ TV too much.   ", "a  watching ", "b  watch", "c  watched  ", "d  watches", "24 There was a thin ................ of oil  on the surface of the water. ", "a  lawyer", "b  law", "c  layer", "d  low ", "25 He ..... at the university about writing for children and the importance of literature.", "a  studied", "b  educated", "c  lectured", "d  learnt", "26 What is the................ in meaning between where and wear?", "a  different", "b  difference", "c  differ", "d  differentiate", "27 A good teacher should................ his students in a friendly way.", "a  talk ", "b  silence ", "c  depress ", "d  speak", "28 He always leaves ................ decisions to his wife. ", "a  intelligence", "b  importance", "c  important", "d  difference", "29 He looked somewhat shamefaced when he ................ his mistake. ", "a  invented", "b  released", "c  realized", "d  recognised ", "30 Security checks have become really ................ at the airport. ", "a  tractor", "b  distract", "c  strict ", "d  district ", "31 Ali is very ................ and all people always ask him for advice. ", "a  sensible", "b  sensitive", "c  sensate", "d  sensational", "32 In nature, some animals show a great degree of ................ .", "a  intelligence", "b  smart", "c  intelligencer", "d  intelligent", "33 My father had a strong ................ on my early childhood.  ", "a  effective", "b  influential", "c  influence ", "d  affect", "34 She felt a persistent ................ at the back of her head   ", "a  explain", "b  plain", "c  ache", "d  lecture ", "35 The long hot summer has led to ................ water shortage. ", "a  survey", "b  serious", "c  serial", "d  series", "36 His ............in God gave him hope during difficult times. ", "a  behave", "b  belief", "c  believe", "d  believer", "37 Mr Al Daifi is ............that the school is not to blame for the situation.", "a  insistent", "b  insist", "c  persist ", "d  assist ", "38 She's hired a ............who specializes in divorce cases. ", "a  lawyer", "b  law", "c  diplomat ", "d  author ", "39 She's just had an article ............in their weekend supplement. ", "a  punished", "b  established", "c  believed", "d  published ", "40 As ............as I am concerned, this is the most interesting book I have ever read.", "a  long", "b  soon", "c  tall", "d  far", "41 Seif ............in physics from Cambridge University.", "a  expert", "b  translated", "c  respected", "d  graduated ", "42 On ............, I earn about one hundred pounds a day. ", "a  average", "b  beverage", "c  merge", "d  leverage ", "43 The state of Florida was hit by a hurricane that ............serious damage.", "a  made", "b  did", "c  gave", "d  took", "44 My father played a ............role in my life.", "a  exciting", "b  pioneer", "c  pioneering", "d  valueless", "45 If you don’t ............traffic rules, you’ll be punished.", "a  obey", "b  crash", "c  break", "d  disobey", "46 She is a graduate ............Cairo University.   ", "a  of", "b  from", "c  in", "d  off ", "47 The questions were ............because my teacher used mysterious words.", "a  confuses", "b  confused", "c  confusing", "d  confusion", "48 The city has eight ............, each with a representative on the City Council.", "a  distinctions", "b  disruptions", "c  districts", "d  disputes", "49 I love escapism so I have read many of the major works of ............", "a  literature", "b  agriculture", "c  trade", "d  industry", "50 Yesterday at five past seven, I ................ my application to the company website.  ", "a  was uploading", "b  would upload", "c  upload", "d  have uploaded ", "51 There is no doubt that the religion has a great............on the Egyptian society.", "a  effectively", "b  influence", "c  affect", "d  lucky", "52 The referee ............a penalty kick. ", "a  rewarding", "b  awarded", "c  rewarded", "d  a ward ", "53 Aya ................ animals, but now she loves them!", "a  doesn’t like", "b  will like", "c  didn’t use to like", "d  is used to liking", "54 ................ he was a student, he was writing short stories.  ", "a  After", "b  As soon as", "c  While", "d  On", "55 There are always economic crises ................ wars.", "a  while", "b  as", "c  when", "d  during", "56 I worry about the ............that violent films may have on children. ", "a  effectively ", "b  affect", "c  effective", "d  effect ", "57 By 2012, I ................ three novels. It was a great achievement for me.  ", "a  had written", "b  will have", "c  had been writing", "d  was writing", "58 Having ................ the visa, I booked a flight to Canada.", "a  received", "b  had received", "c  to receive", "d  receiving", "59 He is punctual. He ................ to coming on time.  ", "a  used", "b  is used", "c  is using", "d  was used ", "60 I as well as my friends ............English yesterday morning.", "a  studied", "b  was studying", "c  were studying ", "d  had studied", "61 What did you do after................ school yesterday?            ", "a  had left", "b  left", "c  leave", "d  leaving", "62 I met one of my old friends while ................ for the school bus.             ", "a  being waited", "b  am waiting", "c  was waiting", "d  waiting", "63 It's late. It's time we ............ home.", "a  go ", "b  gone ", "c  went", "d  goes", "64 ................ waiting hours, all of them felt bored.", "a  After", "b  While", "c  During", "d  On", "65 She used to be a clever student but now she................ .", "a  didn’t", "b  hasn’t", "c  isn’t", "d  doesn’t", "66 I’d rather Nada ................ her mother at the club.", "a  is meeting", "b  meets", "c  met", "d  had met", "67 Are you going to tell Toka what happened, or would you rather I ................ her?", "a  told ", "b  tell ", "c  tells ", "d  had told", "68 Everybody................ a present.   ", "a  have been given ", "b were given", "c  has been given  ", "d  has given", "69 I …………. living here for ten years and I don't want to move.     ", "a  has been            ", "b  were         ", "c  have been      ", "d  had been   ", "70 Jana didn’t sleep until ................ English.                           ", "a  studied", "b  had studied", "c  studying", "d  was studying", "71 I have ................ sent him an e-mail; I expect him to send me a Cheque soon.  ", "a  yet", "b  ever", "c  all ready", "d  recently", "72 After she ................ her homework, she will go to bed.  ", "a  have finished", "b  had finished", "c  was finishing", "d  has finished", "73 ................ repaired, the car looked a new one.", "a  On", "b  Having been", "c  Having", "d  After", "74 He ........ to the bank to draw some money. He will be back in half an hour.  ", "a  has been", "b  has gone", "c  had been", "d  was going", "75 Ali was very unlucky. It ................ every day during his holidays.", "a  was raining ", "b  rained", "c  had rained ", "d  rains ", "76 Aya first met her best friend when she ............at primary school.  ", "a  has been", "b  was being", "c  was", "d  is ", "77 While I was cooking, my husband ............the car.", "a  were washed ", "b  washed", "c  was washing", "d  had washed ", "78 I used to play tennis a lot, but I ................ very much now.", "a  wasn't playing ", "b  never played ", "c  didn't play ", "d  don't play ", "79 What ................ at 10 o'clock last night?", "a  did you do ", "b  have you done ", "c  had you done ", "d  were you doing ", "80 I got home late last night. I was very tired and ................ straight to bed.", "a  go ", "b  went", "c  gone ", "d  goes ", "81 As soon as the police arrested the thief, he ................ to the police station.", "a  had been taken", "b  was taken", "c  had taken", "d  took", "82 No sooner ................ studied English, than she slept.", "a  Jana had", "b  had Jana ", "c  Jana has", "d  has Jana", "83 When I ............my work; I will visit my uncle tomorrow.  ", "a  does", "b  had done", "c  has done", "d  have done", "84 I’d rather you ............this car. It’s a bargain. ", "a  bought", "b  to buy", "c  buy", "d  will buy", "85 It’s two weeks since we last ............  ", "a  met", "b  have met", "c   has met", "d  meet  ", "86 This is the most interesting book I have ............read.  ", "a  already", "b  never", "c  ever", "d  yet  ", "87 I turned off the light before ................ to bed.                                    ", "a  go", "b  going", "c  went", "d  to go", "88 As soon as I ............there, I will call you. ", "a  has arrived", "b  had arrived", "c  arrived", "d  have arrived", "89 He has been training hard ............the last Olympic games.", "a  for ", "b  since ", "c  because ", "d  ago", "90 He as well as his family members  ............to Italy and Turkey.", "a  have gone", "b  have been", "c  has been", "d  has gone", "91 Mostafa has gone on holiday............the last two weeks.", "a  just", "b  since", "c  yet", "d  for", "92 Have you eaten all the food ............? You must have been very hungry.", "a  yet", "b  since", "c  recently", "d  already", "93 It was the first time we ............visited Beni Suef.", "a  have ever", "b   have never", "c  had ever", "d  have just", "94 I haven't slept well since ............her.    ", "a  seen", "b  saw", "c  seeing", "d  see", "95 The plane ............I can see it in the sky!   ", "a  just left", "b  just has left", "c  has left just", "d  has just left ", "96 I ............a cigarette for ten years.", "a  have smoked  ", "b  haven't smoked  ", "c  has smoke   ", "d  hasn't smoked  ", "97  Have you cleaned the flat .......? You' re so quick and efficient.", "a  ever  ", "b  yesterday", "c  already ", "d  never", "98 I hurt my back while ................ in the garden.          ", "a  was working ", "b  working ", "c  is working ", "d  were working");

                    ArrayList<String> qs1 = new ArrayList<>();
                    ArrayList<String> a1s1 = new ArrayList<>();
                    ArrayList<String> a2s1 = new ArrayList<>();
                    ArrayList<String> a3s1 = new ArrayList<>();
                    ArrayList<String> a4s1 = new ArrayList<>();

                    addExam2(strings1, a1s1, a2s1, a3s1, a4s1, ras1, qs1);


                    Collections.shuffle(examList);
                    break;

                case "1": {
                    String[] qs = {"The judge described him as a danger to ............ .", "The astronauts went on ............ spacewalk to replace a broken part.", "You've ......... a great job - thank you Sam.", "I refuse to live under the same roof as that ............ man.", "The operation ......... a complete success.", "She receive her ............ as Mum of the Year.", "She used to be a clever student but now she............", "Our block of flats ………. since 2012.", "Ali is the boy by ………. I was helped.  ", "There's a bus ............ ten minutes.", "We don't have ……….. money to go on holiday right now.", "He wants this job ………...done today.", "Having ........,our homework was marked by Mr Al Daifi.", "We were made ..........a lot of boring history books at school.", "Ali ............ Rami was very ill.", "Don't forget ............ our books with you the next time you come.", "My brothers and I………… our success to the efforts of our parents.", "The first ………… of this poem has six lines.", "Fresh fruit and vegetables form a/an ............ part of a healthy diet.", "I’m ............ of faculty of computer and information systems.", "What time does the moon ............ ?", "He paid......... amounts of money to a charity.", "............ repaired, the car looked a new one.", "They have played tennis three times ………. .", "In the future, most of our work...... by machines.", "I came to apologize for ………. I had done. I hope you will forgive me. ", "She didn't see ………... of the boys.", "\"I ………...the test yet today,\" said Ann.", "Seif told me that he ............ his friend in hospital then.", "I clearly remember ………. everything up.", "Why did you ............ leave early? ", "I............you all about it if you had the time.",};
                    String[] a1s = {"society", "a two-hour", "done", "kind", "proof", "rewarding", "didn’t", "have built", "whose", "every", "so", "will be", "had done", "read", "told", "to bring", "own", "rhyme", "vicious", "a graduate", "arouse", "grade", "On", "last week", "will be doing", "which", "neither", "wasn’t given", "would visit", "to lock  ", "have to", "tell",};
                    String[] a2s = {"social", "two-hour", "made", "exciting", "proved", "award", "hasn’t", "has been built", "who  ", "all", "such", "be", "being done", "reading", "said", "to bringing", "belong   ", "rhythm", "essential", "graduate", "arise", "gradually", "Having been", "next week", "has been done", "that", "either", "hadn’t been given", "had visited", "lock ", "had to", "told",};
                    String[] a3s = {"sociable", "two-hours", "taken", "interesting", "improved", "a ward", "isn’t", "has built", "whom", "each", "too", "to be", "doing", "to read", "said to", "bringing", "owe    ", "poet", "harmful", "degree", "raise", "regularly", "Having", "a week", "will do", "when", "nor", "haven’t been given", "is visiting", "locking", "has to", "would tell",};
                    String[] a4s = {"socially", "two-hour's", "given", "horrible", "won", "reward", "doesn’t", "have been built", "that", "both", "enough", "is", "been done", "having read", "asked", "bring", "lend", "verse", "pest", "pour", "rise", "regular", "After", "this week", "will be done", "what", "both ", "have been given", "was visiting", "locked", "must ", "will tell",};
                    String[] ras = {"society", "a two-hour", "done", "horrible", "proved", "award", "isn’t", "has been built", "whom", "every", "enough", "to be", "been done", "to read", "said", "to bring", "owe", "verse", "essential", "a graduate", "rise", "regular", "Having been", "this week", "will be done", "what", "either", "haven’t been given", "was visiting", "locking", "have to", "would tell",};
                    for (int i = 0; i < qs.length; i++) {
                        String q = qs[i].trim(),
                                a1 = a1s[i].trim(),
                                a2 = a2s[i].trim(),
                                a3 = a3s[i].trim(),
                                a4 = a4s[i].trim(),
                                ra = ras[i].trim();
                        examList.add(new Exam(q, a1, a2, a3, a4, ra));
                    }
                    break;
                }
                case "2": {
                    String[] qs = {"His ............ in God gave him hope during difficult times. ", "We have two grown-up children, both of whom live ............ .", "There was an ............ article on vegetarianism in the paper yesterday. ", "I finally managed to ......... her to go out for a drink with me.", "Schools play an important ......... in society. ", "Doctors kept him ............ on a life-support machine. ", "It's late. It's time we ............ home.", "I as well as my friends ............English yesterday morning.", "How old are you? I ……….16 on Saturday.  	", "He'd ………. come into the room when he died.  ", "Umm Kulthum is a famous singer ………. songs are still enjoyed.  	  ", "It was………. nice coffee that I had two cups.", "I recommend that he ………… his car daily.", "The conference is about………… in Cairo as soon as possible.", "She lives in………… Cairo.", "Hassan ………… sad. ", "You should show more ............ to your parents. ", "My weight ......... when I stop eating sugar. ", "He was copped for driving without a ......... last week. ", "My wallet's gone! I've been............! ", "We can ............ changes in climate with a surprising degree of accuracy. ", "The firm ............ several freelance editors. ", "I remembered ………… her at a party once. ", "Twenty years from now, I think my city………… a fantastic place to live in.", "I feel really hungry. I think I ………… a snack. ", "………… the last two weeks, we have prepared for our graduation party. ", "The rich mud………… reaches Egyptian farmland.", "The teacher gave us………… Homework yesterday. ", "We haven't met each other ………… the last time we were together.", "I wish I ............ my time in the holidays. ", "It............ have rained so much. The ground is not wet.", "Butter............if you leave it out in the sun.",};
                    ArrayList<String> a1s = new ArrayList<>();
                    ArrayList<String> a2s = new ArrayList<>();
                    ArrayList<String> a3s = new ArrayList<>();
                    ArrayList<String> a4s = new ArrayList<>();

                    String[] ras = {"b", "d", "b", "a", "b", "a", "c", "b", "c", "c", "d", "b", "d", "c", "b", "a", "a", "a", "c", "b", "d", "b", "a", "d", "b", "c", "b", "b", "c", "d", "c", "a",};

                    List<String> strings = Arrays.asList("a.  behave", "b. belief", "c. believe", "d. believer", "a. broad", "b. board", "c. aboard", "d. abroad", "a. excited", "b. interesting", "c. interested", "d. interest ", "a. persuade", "b. denied", "c. complained ", "d. admitted", "a. roller", "b. role", "c. roll", "d. rule ", "a. alive", "b. live", "c. life", "d. lives", "a. go ", "b. gone ", "c. went", "d. goes ", "a. studied", "b. was studying", "c. were studying ", "d. had studied", "a. am being", "b. is going to be", "c. will be", "d. am ", "a. better", "b. rather", "c. hardly", "d. prefer", "a. which", "b. that", "c. who", "d. whose", "a. so", "b. such", "c. such a", "d. so much ", "a. checks    ", "b. checked", "c. checking    ", "d. check  ", "a. to hold", "b.  to held", "c. to be held", "d. to be holding", "a. a", "b. no article", "c. an", "d. the", "a. is often   ", "b. often is      ", "c. was often   ", "d. often was  ", "a. respect", "b. respected", "c. respectable", "d. respectful", "a. reduces", "b. increases", "c. goes up ", "d. shortage", "a. grade", "b. will", "c. licence", "d. degree ", "a. rubbed", "b. robbed", "c. stole", "d. stolen ", "a. except", "b. protect", "c. produce", "d. predict", "a. employments", "b. employs", "c. employees", "d. employers", "a. meeting", "b. to meet", "c. meet", "d. met       ", "a. is                        ", "b. is being", "c. is going to be", "d. will be", "a. I'm eating     ", "b.  I'm going to eat     ", "c.  will eat     ", "d.  eat    ", "a. Since    ", "b. At    ", "c. For   ", "d. While     ", "a. don’t     ", "b. no longer  ", "c. doesn’t    ", "d. any longer    ", "a.   a few   ", "b.  some    ", "c.  any    ", "d. many    ", "a. for   ", "b.  before    ", "c.  since    ", "d.  in   ", "a. wouldn't waste", "b. wasted", "c. had wasted", "d. hadn't wasted", "a. mustn't", "b. might", "c. must", "d. can't", "a. melts", "b. will melt", "c. would melt", "d. had melted");

                    addExam(strings, a1s, a2s, a3s, a4s, ras, qs);
                    break;
                }
                case "3": {
                    String[] qs = {"This dictionary ......... both British and American spellings of words. ", "She applied ......... a job with the local newspaper.", "He took a step backwards to ............ her to pass. ", "Nuclear weapons pose a ......... to everyone. ", "A lot of people are against  space ......... as it costs much money. ", "Is it really in the public ............ to publish this information? ", "By 2017, I ............ three European countries.", "I'm hungry. I haven't eaten anything ………. breakfast.", "Look, this glass is cracked. It ………. .", "He kept talking during the lesson, ………. made the teacher angry. ", "............ player has three cards. ", "This story ………...to everybody as the name of the first space pioneer.", "I got my friend ..........me to the airport.", "She asked how long ............ working in my present job.", "Ahmed doesn't smoke any more. This means he stopped............ . ", "He ............ to get up early, so he didn't. ", "It's been a tough six months and I feel I've ………… a few weeks off. ", "The word \"night\" ………… with \"white\". ", "I don’t doubt my father advice. I always take it ………… .", "My new trousers are too long, so my mother is going to......them for me.", "Part of this form seems to be ............ .", "She's an avid reader of ............ novels.", "I wish I ............ where I left my jacket. ", "I ……….. you if I knew what happened that day.", "They ............ mad, the solution they gave is very reasonable. ", "I ............ work from 8.30 to 5.30 every day. ", "I don't know if my boss will let me.......... the day off.", "My children ………...with their homework.", "Let's go. We've waited long ………. .", "I work for a supermarket ……….head office is in Alexandria.", "I'd rather you ………. your car here. ", "............ finished my work, I went home.",};
                    ArrayList<String> a1s = new ArrayList<>();
                    ArrayList<String> a2s = new ArrayList<>();
                    ArrayList<String> a3s = new ArrayList<>();
                    ArrayList<String> a4s = new ArrayList<>();

                    String[] ras = {"b", "c", "a", "d", "a", "b", "b", "c", "a", "a", "d", "c", "a", "d", "c", "a", "b", "c", "d", "b", "b", "d", "b", "c", "b", "a", "c", "d", "d", "d", "b", "c",};

                    List<String> strings = Arrays.asList("a. content", "b. includes", "c. consist ", "d. exclude", "a. with", "b. to", "c. for ", "d. in ", "a. allow", "b. make", "c. have", "d. let ", "a. profit", "b. threaten", "c. benefit", "d. threat", "a. exploration", "b. discovery", "c. invention", "d. foundation", "a. arrest", "b. interest", "c. interested", "d. interesting ", "a. visited ", "b. had visited ", "c. had been visiting ", "d. was visiting ", "a. yet  ", "b. already ", "c. for ", "d. since ", "a. is going to break", "b. is breaking", "c. will break", "d. will be breaking", "a. which  ", "b. who  ", "c. that   ", "d. whose  ", "a. Half", "b. All", "c. Both", "d. Each ", "a. knows", "b. knew", "c. is known", "d. has known ", "a. to drive", "b. driving", "c. drive", "d. driven", "a. have I been", "b. I have been", "c. had I been", "d. I had been ", "a. to smoke", "b. to smoking", "c. smoking", "d. smoke ", "a. didn't need", "b. needn't", "c. mustn't", "d. must", "a. earned   ", "b. gained ", "c. scored    ", "d. acquired    ", "a. verses     ", "b. rhythms     ", "c. rhymes   ", "d. writes      ", "a. out  ", "b. back      ", "c. easy        ", "d. for granted   ", "a. broaden", "b. shorten", "c. deepen", "d. widen", "a. mess", "b. missing", "c. miss", "d. missed ", "a. historically", "b. history", "c. historian", "d. historical ", "a. could know", "b. knew", "c. had known", "d. know", "a. will tell 	", "b. told", "c. would tell ", "d. would told", "a. must be", "b. can’t be", "c. may be", "d. would be ", "a. have to", "b. had to", "c. has to", "d. must ", "a. took", "b. to take", "c. take", "d. taken ", "a. helped", "b. help", "c. are helping", "d. aren’t helped", "a. so", "b. such", "c. too", "d. enough ", "a. who", "b. whom", "c. that", "d. whose", "a. don't park", "b. didn't park ", "c. haven't parked", "d. hadn't parked", "a. After ", "b. Have", "c. Having ", "d. On");

                    addExam(strings, a1s, a2s, a3s, a4s, ras, qs);
                    break;
                }
                case "4": {
                    String[] qs = {"Can you hold this nail in ............ while I hammer it into the door?", "My little sister is very good at making ............ stories. They are very funny!", "............, there's nothing I can do about your problem.", "When people die without a............, it can be a nightmare.", "It was very ............ of you to do her shopping for her. ", "The new tax caused a huge ......... of public anger. ", "When she lived in Japan, she had to get used ............ raw fish.", "After he ………. school, he will be spending six months in India.", "Since ………. her, I haven't slept well.    ", "This time next year, I ………. at university.", "The programmes………..on TV these days aim at solving the people's problems. ", "I was ............ surprised to say anything. ", "The news ………...given on the radio at this moment.", "We know what........the river to get polluted.", "I asked just now ............ .", "I can’t help............ you about it.", "One common ......... of homelessness is separation or divorce. ", "He made no attempt to be ............ . ", "Driving ............ is what most great leaders have in common. ", "As water begins to boil, bubbles ............ ever faster to the surface.", "This report is nonsense and nothing but a ............ of paper. ", "I don't answer questions about my ......... life.", "Physics ………… not very easy to understand.", "It was obvious that he ……….heavily all night. He couldn't stand up.", "It's very hot today. I wish it ............ cold. ", "If he............the papers, he would find a lot of job advertisements. ", "All the pupils understood the lesson yesterday, it ............ difficult.", "Miss Mona was looking forward to............ the title role in the new play.", "\"Did you sleep well?\" I asked him. I asked him if ............ well. ", "The criminal ............ that he killed his neighbour deliberately. ", "The house ……….we live is newly built. 	 ", "Have you ever ………. to Italy?",};
                    ArrayList<String> a1s = new ArrayList<>();
                    ArrayList<String> a2s = new ArrayList<>();
                    ArrayList<String> a3s = new ArrayList<>();
                    ArrayList<String> a4s = new ArrayList<>();

                    String[] ras = {"b", "c", "b", "b", "a", "c", "c", "b", "c", "c", "b", "b", "b", "c", "b", "b", "a", "c", "d", "a", "c", "c", "c", "a", "d", "a", "b", "b", "c", "d", "b", "b",};

                    List<String> strings = Arrays.asList("a. destination", "b. position", "c. site", "d. location", "a. for", "b. out", "c. up", "d. off", "a. Fortunately", "b. Unfortunately", "c. Fortune", "d. Lucky ", "a. wall", "b. will", "c. well", "d. hill ", "a. neighbourly", "b. neighbours", "c. neighbourhood", "d. neighbour", "a. quality", "b. mount", "c. amount", "d. quantity", "a. to eat ", "b. eat ", "c. to eating ", "d. eating ", "a. leaving ", "b. has left ", "c. had left ", "d. left ", "a. seen", "b. saw", "c. seeing", "d. see", "a. will study", "b. will be studied", "c. will be studying", "d. will have studied", "a. which shown", "b. shown", "c. showing", "d. show them", "a. such", "b. too", "c. so", "d. enough ", "a. are being", "b. is being", "c. is been", "d. are been", "a. has", "b. makes", "c. causes", "d. lets", "a. what was the time  ", "b. what the time is", "c. what is the time ", "d. what the time was", "a. to tell", "b. telling", "c. to telling", "d. being told", "a. cause", "b. reason", "c. because", "d. result ", "a. sociably", "b. society", "c. sociable", "d. social", "a. license", "b. grade", "c. ambitious", "d. ambition ", "a. rise", "b. raise", "c. set", "d. fall ", "a. benefit", "b. lost", "c. waste", "d. waist", "a. character", "b. personality", "c. personal", "d. person", "a. are   ", "b. were ", "c. is   ", "d. has ", "a. had been drinking ", "b. was drinking", "c. drank", "d. is drinking", "a. is", "b. would", "c. could", "d. were ", "a. read", "b. reads", "c. had read", "d. would read ", "a. must be", "b. can't have been", "c. could be", "d. can't be", "a. play", "b. playing", "c. be played", "d. being played	", "a. he sleeps", "b. he has slept", "c. he had slept", "d. you slept	", "a. promised", "b. suggested", "c. threatened", "d. admitted", "a. which", "b. in which", "c. whose", "d. who", "a. gone  ", "b. been  ", "c. stayed  ", "d. visited");

                    addExam(strings, a1s, a2s, a3s, a4s, ras, qs);
                    break;
                }
                case "5": {
                    String[] qs = {"Seif ............ in physics from Cambridge University.", "She was ............ a medal for showing supreme bravery. ", "His life story was made ............ a film.", "As a personal trainer to the rich and famous, he ......... over a million dollars a year. ", "His latest book has gone to number two in the ......... list. ", "You've moved the furniture around - the sofa is in a different ......... . ", "............ that red dress when you saw her?", "No sooner ............ studied English, than she slept.", "Her wedding party ………. held next Sunday.   ", "Did you understand ………. he explained?", "Neither restaurant ............ expensive.", "If you leave money there, it will ………....", "The doctor told his nurse if the patient fell asleep, she would tell............ .", "He denied ............ the window glass.", "My son is ill so I ............stay at home.", "If the bark of a tree............, the tree dies. ", "It's believed that a baby ………… its needs by crying . ", "Sailors must be………… during their voyages even if they face terrible conditions.", "The minister made a ………… visit to the scene of the fighting. ", "We should know that water ………… has become of great importance. ", "Through ………… , those people frighten the poor workers to work for them. ", "You must be ………… enough to deal with sudden situations.", "France is ………… European modern country.", "………… I started my profession; I have met a lot of excellent students.", "A: What time ………… your plane take off tomorrow?        B: At 7:30 a.m. ", "My brother ………… the money he needed.  ", "I regret ………… to help her. She proved to be very greedy.  ", "I suggested that All ………… the next school trip. ", "He used to smoke but now he doesn't do ………… . ", "Mohamed Salah is one of Egypt's ………… famous footballers. ", "The manager of my factory promised ………… us a pay rise. ", "Local communities get much benefit when rare species of animals ………… .",};
                    ArrayList<String> a1s = new ArrayList<>();
                    ArrayList<String> a2s = new ArrayList<>();
                    ArrayList<String> a3s = new ArrayList<>();
                    ArrayList<String> a4s = new ArrayList<>();

                    String[] ras = {"a", "d", "d", "c", "b", "d", "a", "b", "a", "d", "d", "a", "c", "c", "c", "d", "b", "a", "d", "d", "c", "c", "a", "d", "c", "d", "b", "a", "b", "b", "c", "c",};

                    List<String> strings = Arrays.asList("a. graduated", "b. translated", "c. respected", "d. expert ", "a. warded", "b. rewarded", "c. rewarding", "d. awarded ", "a. in ", "b. on ", "c. for", "d. into ", "a. inquires", "b. wins", "c. earns ", "d. gains", "a. press ", "b. bestseller", "c. bleach  ", "d. button", "a. location", "b. destination", "c. site", "d. position", "a. Was she wearing ", "b. Has she worn ", "c. Does she wear ", "d. Had she worn ", "a. Jana had", "b. had Jana ", "c. Jana has", "d. has Jana", "a. is being  ", "b. will be  ", "c. is going to be", "d. will have", "a. that ", "b. which ", "c. whose  ", "d. what   ", "a. has", "b. are", "c. were", "d. is", "a. be stolen", "b. be robbed", "c. be stole", "d. have stolen ", "a. them", "b. her", "c. him", "d. me ", "a. break", "b. to break", "c. breaking", "d. to breaking ", "a. mustn’t", "b. has to", "c. have to", "d. may	", "a. was destroyed", "b. destroyed", "c. destroys", "d. is destroyed", "a. contacts", "b. communicates", "c. complains", "d. contracts ", "a. patient", "b. impatient", "c. nervous", "d. tricky", "a. personnel  ", "b. personality     ", "c. urgent    ", "d. personal  ", "a. waste   ", "b. development  ", "c. donation  ", "d. conservation  ", "a. bully    ", "b. bullies    ", "c. bullying    ", "d. bully's   ", "a.  courage    ", "b. impolite      ", "c. flexible       ", "d. discouraged    ", "a. a", "b. an", "c. the", "d. some ", "a. When                 ", "b. Before              ", "c. After", "d. Since", "a. will       ", "b. is   ", "c. does   ", "d. do    ", "a. gave        ", "b. didn't give   ", "c. give   ", "d. was given  ", "a. to promise    ", "b. promising    ", "c. to be promising  ", "d. promise    ", "a. join   ", "b. joined      ", "c. would join     ", "d. joins   ", "a. no longer     ", "b.  any longer     ", "c.  no more     ", "d.  some more    ", "a. more    ", "b. most   ", "c. the most    ", "d. the more ", "a. giving     ", "b. to have given   ", "c. to give   ", "d. has given  ", "a. protected  ", "b. are protecting   ", "c. are protected ", "d. protect   ");

                    addExam(strings, a1s, a2s, a3s, a4s, ras, qs);
                    break;
                }
                case "6": {
                    String[] qs = {"Nowadays, a lot of children are facing ………… because of wars and food crises.", "Debts lead to ………… life. ", "Rescuers used a special ………… to find people trapped in collapsed buildings. ", "Unfortunately, I was ………… of my gold watch last night. ", "The clock was so………… that it kept me awake.", "She struggled to scratch out a living as a ………… artist.", "His colleagues and teachers like him as he is ………… . ", "I haven’t tried Chinese food ………… It has never happened. ", "I can't open the door as I………… my keys.", "Mr. Ahmed has ………… money, but he can afford to buy his needs. ", "I think Brazil………… the World Cup.", "………… the film, Mahmoud fell asleep because it was a boring one.", "I wish the school holidays ............ longer. ", "I can't find my phone anywhere. I ............ it at work. ", "I promised I would be on time. I ............ be late.", "She ............ why I had applied for the job. ", "Mohammed Salah's skills ………… him to score goals.", "He works for a voluntary………… helping homeless people.", "Hello! I know a tree's leaves help it to take ............ light. ", "Different areas of Egypt use different musical............ .", "People often mix us up because we look so ............ . ", "His collected ............ were published in 1998.", "Mohamed Salah scored the goal ………… Egypt to World Cup finals. ", "If the earth had a blue moon and a white moon, I'd prefer ………… white one.", "My father ………… to America on business and he is still there. ", "Now Huda lives in extreme poverty because all the money she had ………… . ", "Would you like ………… the faculty of arts?", "Look at the school time table, the next lesson ………… at nine thirty. ", "After he………… an accident, he was taken to the nearest hospital.", "The centenarian habitually………… to the gym once a week in his youth.", "If only I ............ short. ", "You've been travelling all day. You ............ be tired.",};
                    ArrayList<String> a1s = new ArrayList<>();
                    ArrayList<String> a2s = new ArrayList<>();
                    ArrayList<String> a3s = new ArrayList<>();
                    ArrayList<String> a4s = new ArrayList<>();

                    String[] ras = {"a", "c", "c", "c", "b", "a", "b", "b", "b", "b", "a", "d", "b", "d", "c", "b", "a", "d", "d", "b", "b", "d", "c", "b", "c", "c", "b", "c", "a", "b", "c", "a",};

                    List<String> strings = Arrays.asList("a. danger", "b. dangerous ", "c. endanger   ", "d. dangerously ", "a. misery    ", "b. miser  ", "c. miserable    ", "d. mineral  ", "a. realize ", "b. advice", "c. device  ", "d. advance     ", "a. stolen    ", "b. taken    ", "c. robbed ", "d. owed  ", "a. calm", "b. noisy", "c. helpful", "d. annoyed", "a. professional", "b. amateur", "c. volunteer", "d.  donor ", "a. behaves politely", "b. usually polite ", "c. usual politely  ", "d. usually politely  ", "a. yet  ", "b. before      ", "c. ago    ", "d. since  ", "a. lost ", "b. have lost     ", "c. loses     ", "d. had lost   ", "a. much    ", "b. a little     ", "c. any     ", "d. little  ", "a. will win", "b. going to win", "c. is winning", "d. wins", "a. While", "b. When", "c. On", "d.  During ", "a. are", "b. were", "c. has been", "d. have been", "a. might leave", "b. can't have left", "c. must have left", "d. might have left", "a. have to", "b. must", "c. mustn't", "d. don't have to ", "a. said to", "b. asked", "c. told", "d. said", "a.  enable", "b.  ability", "c.  able", "d.  capable", "a. destination", "b. application", "c. communication", "d. organisation", "a. off", "b. out", "c. on", "d. in", "a. machines", "b. instruments", "c. tools", "d. devices", "a. like", "b. similar", "c. same", "d. family", "a. novelists", "b. pots", "c. poets", "d. poems", "a. send", "b. sent", "c. to send", "d. is sent ", "a.  an", "b. the  ", "c. a  ", "d. some  ", "a. has been  ", "b. went      ", "c. has gone        ", "d. is going    ", "a. been lost  ", "b. was lost     ", "c. has been lost    ", "d. is being lost  ", "a. joining ", "b. to join ", "c. joined.", "d. join     ", "a. will start        ", "b. has started    ", "c. starts   ", "d. may start   ", "a. made    ", "b. has made   ", "c. makes   ", "d. was made   ", "a. go", "b. went", "c. goes", "d. going", "a. didn't", "b. couldn't", "c. weren't", "d. wouldn't", "a. must", "b. can't", "c. mustn't", "d. might");

                    addExam(strings, a1s, a2s, a3s, a4s, ras, qs);
                    break;
                }
                case "7": {
                    String[] qs = {"What is the most ............ aspect of your work?", "We always ............ our wedding anniversary with a bottle of champagne.", "Please feel free to ............ me if you don't understand anything. ", "When I got home, I found a ............ message in front of the door. ", "I'd like to take  ……… five minutes to finish the exam.", "I know I haven't ......... more revision for tomorrow's exam.", "Are you going to tell Toka what happened, or would you rather I ............ her?", "They ............ able to come because they were so busy.", "Over the years, Egyptian people …………. a lot.", "Mr Wagah is very tired. He has ………. very hard all day.", "Don't phone me now. I………. a shower.", "I read an article ………. the writer expresses his opinion of globalization.", "He is lucky to marry ………. pretty a woman! I hope I am in his shoes! ", "My father hates ………...waiting in queues. ", "I didn’t believe ........ .", "Don't spend too much time............ computer games. ", "My father can still recite the ............ he learned off by heart at school. ", "How would ............ people escape in an emergency? ", "He graduated ............ the university last June.", "Surely it is wrong to try to impose western ......... on these people? ", "He has already ......... his main ambition in life - to become wealthy. ", "I think I locked the door but I'll go back and check just to ......... sure.", "I didn't buy the mobile ............the shop had been close. ", "It ………. three years since you phoned me, Ali.", "He as well as his family members ………. to Italy and Turkey.", "I can't see the match tomorrow evening. I ........for my English exams.", "Fayoum, ……….is located in Egypt, is a beautiful city. ", "She said she couldn't see me. She was too busy ............ other things.", "You can’t come in. She ………...interviewed for the TV.", "She said that she ............ going to learn to drive tomorrow.", "............ waiting hours, all of them felt bored.", "I can manage the shopping alone. You ............ go with me.",};
                    ArrayList<String> a1s = new ArrayList<>();
                    ArrayList<String> a2s = new ArrayList<>();
                    ArrayList<String> a3s = new ArrayList<>();
                    ArrayList<String> a4s = new ArrayList<>();

                    String[] ras = {"a", "c", "a", "d", "b", "a", "a", "d", "a", "a", "d", "d", "b", "b", "c", "b", "c", "a", "b", "c", "b", "c", "b", "a", "c", "c", "c", "b", "b", "b", "c", "d",};

                    List<String> strings = Arrays.asList("a. rewarding", "b. reward", "c. award", "d. a ward ", "a. celebration", "b. celebrity", "c. celebrate", "d. celebrant", "a. interrupt", "b. park", "c. transplant", "d. defeat ", "a. mysteriousness", "b. mysteriously", "c. mystery", "d. mysterious", "a. other     ", "b. another         ", "c. others              ", "d. the other", "a. done", "b. made", "c. gone", "d. given", "a. told ", "b. tell ", "c. tells ", "d. had told ", "a. aren't ", "b. didn't ", "c. wasn't ", "d. weren't ", "a. have changed ", "b. had changed", "c. are changing", "d. has changed ", "a. been working ", "b. working ", "c. been worked", "d. worked ", "a. have", "b. will have had", "c. will have", "d. am having", "a. that  ", "b. which", "c. at which   ", "d. in which", "a. enough", "b. such a", "c. so", "d. too", "a. been kept", "b. being kept", "c. to be keeping", "d. keeping ", "a. what said", "b. which said", "c. what had been said", "d. what had said", "a. play", "b. playing", "c. played", "d. to play ", "a. poets ", "b. diplomats ", "c. poems", "d. pioneers", "a. disabled", "b. ability", "c. disability", "d. able", "a. of", "b. from", "c. in", "d. on ", "a. industrial", "b. agricultural", "c. culture ", "d. cultural", "a. done", "b. achieved ", "c. gave", "d. took", "a. get", "b. take", "c. make ", "d. do", "a. until", "b. since", "c. before", "d. after", "a. is", "b. has", "c. had", "d. was", "a. have gone", "b. have been", "c. has been", "d. has gone", "a. will revise", "b. will have revised", "c. will be revising", "d. revise ", "a. whose ", "b. that ", "c. which   ", "d. where  ", "a. do", "b. to do", "c. to doing", "d. doing", "a. is", "b. is being", "c. was", "d. has been ", "a. was", "b. is", "c. was being", "d. had ", "a. After", "b. While", "c. During", "d. On ", "a. must", "b. doesn't have to", "c. need", "d. don't have to");

                    addExam(strings, a1s, a2s, a3s, a4s, ras, qs);
                    break;
                }
                case "8": {
                    String[] qs = {"Strong winds had caused ......... damage to the roof. ", "Amy's at university now and doing ......... well.", "The course includes an ......... module. ", "Jana tried to ……….all her doubts but she failed.", "The firm ......... several freelance editors. ", "Yoga is a very ......... technique for combating stress. ", "I............ a car accident while coming to school.", "Toka is starving………. she has eaten nothing for 48 hours. ", "………. have these students studied French? ", "The play ..........at seven every evening. ", "The man, ………. the car, is called Seif.", "Do you want tea or coffee? ............ I don't mind.", "How many times ………...playing football?", "I had my back  ..........by my doctor last month.", "She asked ............ I was willing to travel.", "Hani ............ wear a suit to work, but he usually does.", "The competition is open to anyone ......... 18 or over. ", "Don't ………. someone who has nothing to lose. ", "His teacher ............ him as a noisy, disruptive influence in class. ", "Why is it so important to ............ stress?", "His latest film is ............ as a 'romantic comedy'. ", "I've always got ............ well with Henry. ", "I wish I............ harder when I had the time.", "If water boils, it............ steam.", "She............better leave now if she wants to catch the train.", "I'm sure what he said was correct. He ............ mistaken.", "Would you mind............ the door, please?", "She told him that she had visited Beni Suef............ .", "Our teacher told us that the earth ............ the sun. ", "The thieves .......by anyone.", "The book was so ……….. that I couldn't put it down.", "The company, ………. my father worked for, exported goods to Europe.",};
                    ArrayList<String> a1s = new ArrayList<>();
                    ArrayList<String> a2s = new ArrayList<>();
                    ArrayList<String> a3s = new ArrayList<>();
                    ArrayList<String> a4s = new ArrayList<>();

                    String[] ras = {"b", "c", "a", "a", "d", "c", "b", "d", "c", "c", "c", "a", "a", "b", "a", "c", "b", "d", "b", "c", "b", "b", "d", "b", "d", "a", "c", "c", "a", "c", "a", "a",};

                    List<String> strings = Arrays.asList("a. source", "b. serious", "c. serial", "d. series", "a. quarter", "b. quiet", "c. quite ", "d. quit ", "a. online", "b. out of line", "c. in lines ", "d. line", "a. remove", "b. improve", "c. prove", "d. move", "a. employable", "b. employee", "c. employer", "d. employs ", "a. effectively ", "b. affect", "c. effective", "d. effect ", "a. was seeing ", "b. saw", "c. see", "d. have seen ", "a. so         ", "b. after", "c. for", "d. since", "a. For then", "b. For when", "c. Since when", "d. How long ago ", "a. will begin", "b. is going to begin", "c. begins", "d. is beginning ", "a. that is driving  ", "b. driven  ", "c. driving    ", "d. who driving", "a. Either", "b. Neither", "c. Half", "d. Every", "a. have you been hurt", "b. did you hurt", "c. were you hurting", "d. are you hurting", "a. check", "b. checked", "c. to check", "d. checking", "a. whether", "b. weather", "c. unless", "d. that ", "a. need to", "b. have to", "c. doesn't have to", "d. must", "a. ages", "b. aged", "c. aging", "d. age ", "a. change", "b. chance", "c. shy", "d. challenge", "a. took", "b. described", "c. gave", "d. presses ", "a. get", "b. earn", "c. manage", "d. give ", "a. based", "b. described", "c. worked", "d. trained", "a. in", "b. on", "c. to", "d. up ", "a. would work", "b. have worked", "c. will work", "d. had worked ", "a. produce", "b. produces", "c. will produce", "d. would produce", "a. will", "b. would", "c. could", "d. had", "a. can't have been", "b. may be", "c. must be", "d. can't be", "a. to close", "b. being closed", "c. closing", "d. close	", "a. the day after", "b. then", "c. the previous day", "d. the following day", "a. orbits", "b. would orbit", "c. was orbiting", "d. orbited", "a. saw", "b. haven't seen", "c. weren’t seen", "d. didn’t see", "a. good", "b. a good", "c. will", "d. a well", "a. which", "b. whose", "c. where", "d. that");

                    addExam(strings, a1s, a2s, a3s, a4s, ras, qs);
                    break;
                }
                case "9": {
                    String[] qs = {"No one else helped me . I did it ………… my own. ", "This stamp is ………… ; there are no others like it.  ", "I can hear many ………… in the living room. ", "I think work here will give you a lot of ………… that may help you in your life. ", "………… should be considered a crime. ", "Writers are always ………… by adventurous travel. ", "I advised her ………. obey her parents again. ", "You are kidding! Have you drunk all the juice ……….?", "Once I ………. this novel, I'll give it to my old friend.", "Having ........ the shopping, mother started to prepare lunch. 	 ", "The woman………. the wallet was stolen, called the police.", "In football, each team ............ eleven players.", "A mystery is something that can't ……….. .", "He looked forward to ..........his homework done by his classmate. ", "She told the police that her purse had been robbed the............ .", "The boss asked if he ............ a favour the night before.	", "The children were asked to stand in a ………… . ", "What kind of literature do you enjoy reading? -………… stories. ", "Do you want to swim in this stormy weather? Don’t be ………… ", "The minister made a very important ………… yesterday.", "We mustn’t ………… traffic rules.", "I've ………… my money on this mobile it broke down 2 days after buying it.", "Aya studied English for years and she ………… Oliver Twist, then. ", "You should avoid………… friends with such bad people.", "………… I was talking on the phone, the doorbell rang.", "My father has ………… the airport. He is on his way there. ", "Fear is ………… universal weakness. ", "He didn't use to be late, but now he ………… .", "The baby is crying loudly, his mother ………… him right now.", "Money ………… to Egypt by the tourists. ", "They won't have lunch before Ahlam………… ", "If he read the story, ............ he give it to me?  ",};
                    ArrayList<String> a1s = new ArrayList<>();
                    ArrayList<String> a2s = new ArrayList<>();
                    ArrayList<String> a3s = new ArrayList<>();
                    ArrayList<String> a4s = new ArrayList<>();

                    String[] ras = {"a", "b", "d", "b", "c", "c", "a", "a", "a", "d", "d", "d", "b", "b", "c", "d", "c", "a", "a", "a", "b", "a", "a", "b", "c", "d", "d", "c", "b", "c", "d", "c",};

                    List<String> strings = Arrays.asList("a. on", "b. of", "c. at ", "d. from ", "a. united ", "b. unique  ", "c. union  ", "d. onion  ", "a. noise ", "b. sound ", "c. voice  ", "d. voices      ", "a. experiments      ", "b. experience    ", "c. expects     ", "d. experiences    ", "a. Belly     ", "b. Bully     ", "c. Bullying   ", "d. Belling      ", "a. inspected  ", "b. expected   ", "c. inspired  ", "d. expired  ", "a. to", "b. that", "c. not to", "d. don't", "a. already", "b. just", "c. ever ", "d. yet ", "a. have read", "b. has read", "c. had read", "d. was reading ", "a. do ", "b. doing ", "c. been done ", "d. done", "a. from who", "b. whom", "c. whose        ", "d. from whom", "a. are", "b. were", "c. have", "d. has", "a. being explained", "b. be explained", "c. explain", "d. been explained ", "a. have", "b. having", "c. making", "d. get ", "a. following day", "b. day previous", "c. day before", "d. next day", "a. would do", "b. did", "c. does", "d. had done", "a. law   ", "b. raw  ", "c. row  ", "d. rue  ", "a. Adventure  ", "b. Adventurous      ", "c. Adverts        ", "d. Apps    ", "a. crazy    ", "b. wise  ", "c. right   ", "d. correct  ", "a. announcement", "b. advertisement", "c. retirement      ", "d. population   ", "a. take       ", "b. break   ", "c. undertake   ", "d. follow  ", "a.  wasted", "b.  spent", "c. borrowed", "d. lent", "a. read", "b. reads", "c. is reading", "d. is read ", "a. to make", "b. making", "c. makes", "d. to making", "a. Since", "b. during", "c. While", "d. after", "a. been in    ", "b. gone in  ", "c. been to    ", "d. gone to  ", "a. an   ", "b. no article      ", "c. the      ", "d. a ", "a. did    ", "b. do    ", "c. is", "d. does", "a. is going to feed  ", "b. will feed   ", "c. going to feed   ", "d. feeds ", "a. brings  ", "b. is brining      ", "c. is brought", "d.  are brought ", "a. arrived  ", "b. arriving ", "c. arrive ", "d. has arrived ", "a. will", "b. Does", "c. would", "d. would have");

                    addExam(strings, a1s, a2s, a3s, a4s, ras, qs);
                    break;
                }
                case "10": {
                    String[] qs = {"They tend to build a ………… club in our town.", "Being hardworking, he could ………… everyone's admiration.", "The plumber came to ......... the burst pipe. ", "Egypt's supposed to build hotels with environmentally- ………… materials.", "The famous sergeant ………… a successful heart transplant operation.    ", "The first line of Cairo underground is 44 km in ............ . ", "Toka is clever. I think she ……….the full mark.", "What ..........at midday yesterday?	", "Tourists ……….stay cost much, moved to a cheaper hotel. 	 ", "Yesterday, the robbers..........the staff lie on the floor.", "He admitted............the car but denied doing it by himself.", "Ali can't come out with us this evening. He ............ work late. ", "That restaurant ............ be very good. It's always empty.", "If he ……….. thirsty he would have drunk some water.", "If it............tomorrow, I will go for a walk. ", "I feel sick I wish I ……….. so much.", "Some athletes take drugs to ......... their performance. ", "She works ......... a consultant for a design company.", "The factory ......... most of its workers with robots. ", "The house is not really......... for a large family.", "You must follow the ......... or you will be punished.  ", "We give help and support to people in need, as well as........ money for charities.", "Have you finished your works ……….?    You always inspire me, Seif! ", "I told him all .......... I know about the solar system. 	 ", "This school ………...next year.", "The first coins in America .......in 1752. They were not regular in shape.", "She said she ............ her friend for ages.", "Why didn’t you try............ yourself a job?", "We've got plenty of time. We............ hurry. ", "If it rains tomorrow, we............at home. ", "People who live near volcanoes leave home if it............ .", "The tourists ............ walking until they have reached the top of the mountain.",};
                    ArrayList<String> a1s = new ArrayList<>();
                    ArrayList<String> a2s = new ArrayList<>();
                    ArrayList<String> a3s = new ArrayList<>();
                    ArrayList<String> a4s = new ArrayList<>();

                    String[] ras = {"c", "a", "b", "b", "b", "d", "c", "b", "a", "a", "a", "c", "d", "b", "a", "b", "c", "a", "a", "a", "d", "d", "a", "a", "c", "b", "b", "d", "a", "a", "d", "d",};

                    List<String> strings = Arrays.asList("a. sport", "b. sporting", "c. sports", "d. sporty", "a. gain", "b. earn", "c.  win ", "d. score", "a. remind", "b. mend", "c. mind", "d. reform", "a. friend", "b.  friendly ", "c.  friendship", "d.  friends ", "a. made", "b. had", "c. did", "d.  gave ", "a. width", "b. height", "c. diameter", "d. length", "a. is going to get", "b. will have got", "c. will get", "d. will be getting", "a. are you doing ", "b. were you doing ", "c. you were doing ", "d. have you done", "a. whose", "b. who", "c. who's", "d. whom", "a. made", "b. got", "c. have", "d. allowed", "a. stealing", "b. to steal", "c. to stealing", "d. steal ", "a. have to", "b. had to", "c. has to", "d. mustn't ", "a. must", "b. should", "c. mustn't", "d. can't", "a. had had", "b. had been", "c. were ", "d. was", "a. doesn't rain", "b. won't rain", "c. didn't rain", "d. hadn't rained ", "a. had eaten", "b. hadn't eaten", "c. wouldn't have eaten ", "d. wouldn't", "a. proof", "b. provide", "c. improve", "d. prove", "a. as", "b. on", "c. for", "d. in ", "a. replaced", "b. placed", "c. replacement ", "d. replacing", "a. suitable", "b. suit", "c. responsible ", "d. argue ", "a. rollers", "b. rolls", "c. roles", "d. rules", "a. raised", "b. to raise", "c. raise ", "d. raising", "a. already", "b. yet", "c. recently", "d. ever", "a. that", "b. which", "c. this", "d. whom", "a. will close", "b. is closed", "c. will be closed", "d. would be closed", "a. are made", "b. were made", "c. was made", "d. is made", "a. didn’t", "b. hadn’t seen", "c. hasn’t seen", "d. doesn’t see ", "a. found", "b. to finding", "c. finding", "d. to find", "a. needn't", "b. have to", "c. doesn't have to", "d. has to", "a. will stay", "b. stayed", "c. stay", "d. might stay ", "a. will erupt", "b. would erupt", "c. erupted", "d. erupts", "a. didn’t stop", "b. stopped", "c. haven’t stopped", "d. won’t stop");

                    addExam(strings, a1s, a2s, a3s, a4s, ras, qs);
                    break;
                }
                case "11": {
                    String[] qs = {"Her department is responsible ......... overseeing the councils.", "We shouldn't ............ the rules.", "He sent the 400-page manuscript to his ............ . ", "Don't go any closer - it might not be ............ . ", "I'm going to make an effort that help me to be a bit more ............ . ", "We need to ............ public awareness of the disease. ", "The examiner made us .......our identification in order to be admitted to the test.", "You should have your visa ..........before it expires.", "Omnia ............ that she had a headache.", "He avoided............ the most difficult questions. ", "She decided............ to Spain for her holidays.", "You ............ park here your car until you pay for it.", "I expect he will arrive soon. I wish he............ do that. ", "She would have danced with him if he ……….. her.", "I don't think a ………… is enough for me to finish this research at home. ", "I ………… to the USA this month. It was a very useful experience. ", "She's very good at coping in ............ situations. ", "The length of time depends ............ the sport you are training for.", "He paid ............ amounts of money to a charity. ", "I worry about the ......... that violent films may have on children. ", "It's a disease which affects ............ older people. ", "He claims to have met the President , but I don't ............ him. ", "Have you ever been to Cairo?     – Yes, I………… there in 2005. ", "I can't forget………… It was a painful experience. ", "I couldn’t watch the film as my brother ………… a football match. ", "Parents shouldn't allow ………… among the members of the family.", "Ain Shims University ………… in 1946. Now it consists of different colleges.", "While I ………… at home, my uncle suddenly returned from Italy .", "They are talking to ………… 8-year old boy called, Ali. ", "You needn't ............You could have taken your time. ", "Can I talk to you, please? Sorry, I............ go now. I'm late. ", "............ finished my work, I went home.",};
                    ArrayList<String> a1s = new ArrayList<>();
                    ArrayList<String> a2s = new ArrayList<>();
                    ArrayList<String> a3s = new ArrayList<>();
                    ArrayList<String> a4s = new ArrayList<>();

                    String[] ras = {"c", "b", "b", "d", "c", "a", "b", "a", "a", "b", "a", "c", "b", "a", "a", "c", "b", "d", "b", "d", "b", "b", "d", "b", "b", "d", "b", "a", "a", "b", "d", "c"};

                    List<String> strings = Arrays.asList("a. to", "b. of", "c. for", "d. with", "a. lose", "b. break", "c. follow", "d. fellow ", "a. stuff", "b. publisher", "c. punisher", "d. robin ", "a. safety", "b. sofa", "c. save", "d. safe", "a. social", "b. society", "c. sociable", "d. community ", "a. increase", "b. decrease", "c. reduce", "d. lack", "a. showing", "b. show", "c. to show", "d. showed", "a. extended", "b. to extend", "c. be extended", "d. to be extended", "a. complained", "b. told", "c. asked", "d. warned ", "a. to answer", "b. answering", "c. answer", "d. answered ", "a. to go", "b. go", "c. goes", "d. to going	", "a. mustn’t ", "b. must ", "c. can't ", "d. must be", "a. would", "b. could", "c. can", "d. may ", "a. had asked", "b. asked", "c. would ask", "d. would have asked", "a. ten-day-holiday", "b. tens-day-holiday  ", "c.  ten-day-holidays", "d. ten-days-holidays   ", "a. went    ", "b. had been   ", "c. have gone  ", "d. have been  ", "a. pressure", "b. stressful", "c. stress", "d. press ", "a. with", "b. for", "c. in", "d. on ", "a. gradual", "b. regular", "c. regularly", "d. gradually", "a. effectively ", "b. affect", "c. effective", "d. effect ", "a. meanly", "b. mainly", "c. mean", "d. main ", "a. belief", "b. believe", "c. believer", "d. truth ", "a. have   ", "b. have gone  ", "c.  have been", "d. went   ", "a. to bully    ", "b. being bullied   ", "c. bully  ", "d. bullied   ", "a. has watched     ", "b. was watching    ", "c. watched  ", "d. had watched", "a. bully    ", "b. to bully   ", "c. bullied  ", "d. bullying    ", "a. was designed  ", "b. was founded ", "c. was published ", "d. was admired ", "a. was", "b. was being", "c. am", "d. being ", "a. an   ", "b. a  ", "c. some   ", "d. these  ", "a. rushing", "b. have rushed", "c. rushed", "d. rush", "a. mustn't", "b. has to", "c. need", "d. must ", "a. After ", "b. Have", "c. Having ", "d. On");

                    addExam(strings, a1s, a2s, a3s, a4s, ras, qs);
                    break;
                }
                case "12": {
                    String[] qs = {"Do ............ me because I'm likely to forget. ", "He is ............ working on his first novel.", "After a lengthy and ............ divorce, she vowed never to remarry.", "The family are ............ arrangements for his funeral.", "By the time they ............ the summit they were exhausted. ", "She was a fiercely independent lady who refused to............ on anyone. ", "I'd hate to lose ......... with my old school friends.", "There's nobody at home. They ............ have gone out. ", "He............ been injured. There was blood on his face.", "Perhaps Helen is in her office. She ............ in her office.", "Fareeda borrowed her friend's car while hers ………… .", "Hagar's friends admire her as she ………… helpful.", "He got a job as ………… bus conductor.", "Our plane ………… off at 7.30 a.m. next Friday.", "When we………… for the bus to come, a passer-by insisted to give us a lift.", "She………… in Paris when she met her husband.", "This particular stretch of coast is especially ......... with walkers. ", "The refugees were at sea for forty days before reaching ......... . ", "Night ............ and landings are banned at this airport. ", "A heavy fall of snow had disrupted the city's transport ............ . ", "First,......... sure the printer has enough paper in it.", "I'd like to speak ............ English. ", "Earning much money and spending It wisely ………… easily as thought.", "Several children ………… with disabilities suffer from paralysis.", "I………… here since I left school.", "The students ………… how to do the experiment yesterday.", "The trousers you bought for Amr's birthday ………… fit him.  ", "He ………… playing squash. ", "I ………… an engineer. This is my intention.", "He was made ………… a fine for driving his car last.", "The man ………… his mobile while driving his car. ", "He spends so much money. He............ be rich."};
                    ArrayList<String> a1s = new ArrayList<>();
                    ArrayList<String> a2s = new ArrayList<>();
                    ArrayList<String> a3s = new ArrayList<>();
                    ArrayList<String> a4s = new ArrayList<>();

                    String[] ras = {"b", "b", "b", "a", "b", "d", "b", "a", "c", "d", "c", "d", "d", "c", "c", "a", "a", "b", "a", "b", "b", "c", "b", "d", "b", "b", "b", "c", "c", "a", "c", "c",};

                    List<String> strings = Arrays.asList("a. mend", "b. remind", "c. remember", "d. mind", "a. cure", "b. currently", "c. current", "d. currency ", "a. powerful", "b. painful", "c. tasteful", "d. hopeful", "a. making", "b. doing", "c. talking", "d. going", "a. go", "b. reached", "c. arrive", "d. get", "a. enrol", "b. keen", "c. based", "d. depend", "a. continent", "b. contact", "c. contract", "d. content", "a. must", "b. can't", "c. mustn't", "d. should", "a. can't have", "b. mustn't have", "c. must have", "d. should have", "a. must be", "b. must have been", "c. might be", "d. might have been", "a. was repairing", "b. was repaired", "c. was being repaired", "d. repaired", "a. be always", "b. always be", "c. always is", "d. is always", "a. no article", "b. an", "c. the", "d. a", "a. will take", "b. is going to take", "c. takes", "d. is taking ", "a. waited", "b. wait", "c. we're waiting", "d.  are waiting", "a.  was studying", "b.  has studied", "c. had studied", "d. studied", "a. popular", "b. famous", "c. keen", "d. fond", "a. dust", "b. land", "c. soil", "d. earth ", "a. take-offs", "b. take-ons", "c. take-ins", "d. take-places ", "a. process", "b. system", "c. discipline", "d. web ", "a. give", "b. make", "c. do", "d. take ", "a. fluency", "b. flew", "c. fluently", "d. fluent ", "a.  isn't done  ", "b.  aren't done     ", "c. isn't doing   ", "d.  aren't doing      ", "a. were born", "b. are born", "c. was born", "d.  born ", "a. had worked", "b.  have worked", "c. work", "d. was going to work ", "a. showed ", "b. were shown ", "c. were showing  ", "d. would show", "a. doesn’t    ", "b. don’t     ", "c. isn't    ", "d. aren't ", "a. used to   ", "b. use to ", "c. is used to    ", "d. used", "a. will become", "b. become", "c. am going to become", "d. am becoming  ", "a. to pay    ", "b. pay    ", "c. paying  ", "d. paid   ", "a. uses  ", "b. was used    ", "c. was using ", "d. has used   ", "a. can't", "b. shouldn't", "c. must", "d. won't");

                    addExam(strings, a1s, a2s, a3s, a4s, ras, qs);
                    break;
                }
            }
        } else if (getIntent().getExtras().getString("type").equals("Rev")) {
            switch (testId) {
                case "R1": {
                    String[] ras2 = {"d", "b", "d", "d", "d", "c", "d", "b", "a", "d", "a", "a", "b", "c", "b", "c", "d", "a", "c", "a", "a", "d", "c", "b", "c", "a", "a", "b", "b", "a", "a", "d", "b", "c", "c", "b", "c", "a", "b", "a", "a", "a", "a", "d", "a", "b", "b", "a", "d", "a",};
                    List<String> strings2 = Arrays.asList("1) ....... people encounter many practical problems in the workplace.", "a. Ability", "b. Disability", "c. Enabled", "d. Disabled", "2) \"Are you going to the bank?\" \"No, I've already ....... to the bank.\"", "a. gone", "b. been", "c. visited", "d. reached", "3) ....... he phoned Angie before he went to see her in Ashmoun?", "a. Will", "b. Did", "c. Has", "d. Had", "4) ....... is something that makes you feel proud and happy.", "a. Discipline", "b. Shyness", "c. shame", "d. Honour", "5) ....... is the practice of making people obey rules and orders.", "a. Archaeology", "b. Association", "c. Responsibility", "d. Discipline", "6) ....... means behaviour that is typical of a kind or concerned father.", "a. Brotherly", "b. Sisterly", "c. Fatherly", "d. Featherly", "7) ....... my visit to the museum, I saw many monuments.", "a. While", "b. Just as", "c. As", "d. During", "8) ....... put the child to bed, I phoned the doctor.", "a. No sooner", "b. Having", "c. After", "d. On", "9) ....... the manual, I found I could use the computer easily.", "a. Having read", "b. To read", "c. Had read", "d. After read", "10) I ....... three cups of coffee yesterday.", "a. had drunk", "b. drink", "c. have drunk ", "d. drank", "11) ....... would perhaps make good politicians, as they know what can and cannot be done in law.", "a. Lawyers", "b. Surgeons", "c. Nurses", "d. Archaeologists", "12) .......I was out shopping, I saw three of my friends", "a. While", "b. Although", "c. Because", "d. On", "13) \"Would you like another cup of coffee?\" - \"No thanks. I ....... three cups.", "a. already had", "b. had already had", "c. already have", "d. will already have", "14) A ....... is the company that makes something available to read.", "a. designer", "b. chef", "c. publisher", "d. polisher", "15) A ....... of dust covered everything in the old house.", "a. lawyer", "b. layer", "c. liar", "d. player", "16) A / An....... person is someone who is quiet and does not often laugh.", "a. silent", "b. important", "c. serious", "d. idiot", "17) A motorcycling accident had left her severely .......", "a. ability", "b. enable", "c. able", "d. disabled", "18) A person who has been a soldier could ....... a good policeman.", "a. do", "b. make", "c. has", "d. take", "19) A....... is someone who works in politics, especially an elected member of the government.", "a. diplomat ", "b. technician", "c. politician", "d. electrician", "20) A/ An .......is an organisation for people with the same interests, or who do the same kind of work.", "a. association", "b. lecture", "c. discipline", "d. ministry", "21) A: Has the plane landed? B: Yes, it ....... ten minutes ago.", "a. landed", "b. had landed", "c. has landed", "d. will land", "22) After ....... from Cairo university, he traveled abroad.", "a. had graduated", "b. graduated", "c. graduate", "d. graduating", "23) After ....... the doors and the windows, I went to bed.", "a. locked", "b. had locked", "c. locking", "d. lock", "24) After the house ....... painted, we furnished it.", "a. had", "b. had been", "c. has been", "d. is", "25) Ahmed ....... in love with Mona since they were teenagers.", "a. falls", "b. fell", "c. has fallen", "d. will fall", "26) Ahmed hasn’t seen Maha since she .......", "a. graduate", "b. graduates", "c. is graduating", "d. has graduated", "27) Airlines are legally ....... for the safety of their passengers.", "a. responsible", "b. response", "c. responsive", "d. irresponsible", "28) Ali is a very close friend of mine. We ....... each other for years.", "a. know", "b. have known", "c. have been knowing", "d. had known", "29) Amal ....... the music so that she could hear what her mother had to say.", "a. lightened", "b. silenced", "c. ached", "d. turned up", "30) Amal is so ....... she gets up every morning at 5:00 to go jogging.", "a. disciplined", "b. complained", "c. explained", "d. equipped", "31) Amal isn't here at the moment. She's ....... to the shop to get a newspaper.", "a. gone", "b. been", "c. being", "d. be", "32) Amany phoned me while the dinner ....... .", "a. was cooking", "b. cooked", "c. cooks ", "d. was being cooked", "33) Amany was really hungry because she ....... all day.", "a. hasn't eaten", "b. hadn't eaten", "c. doesn't eat", "d. wasn't eating", "34) An / A .. measuring 6.1 on the Richter scale struck southern California on June 28.", "a. drought	", "b. flood", "c. earthquake", "d. volcano", "35) Are your friends here yet? Yes, they ....... .", "a. had just arrived", "b. just arrive", "c. have just arrived", "d. arrived", "36) As soon as she ....... the book, she wanted to see the film.", "a. finishing", "b. had finished", "c. has finished", "d. finishes", "37) As soon as we arrived at school, the first lesson ....... . We missed it.", "a. was beginning", "b. began", "c. had begun", "d. has begun", "38) As the sun went ....... below the horizon, all farmers returned home.", "a. down", "b. up", "c. out", "d. into", "39) At this time yesterday, I ....... an essay.", "a. write", "b. was writing", "c. have written", "d. written", "40) Before ....... to bed, the baby had drunk all his milk.", "a. going", "b. to go", "c. went", "d. being gone", "41) By 2015, I ....... three novels. It was a great achievement for me.", "a. had written", "b. will have written", "c. have been writing", "d. was writing", "42) By the time he was 15, my daughter....... all the Holy Quran by heart.", "a. had kept", "b. kept", "c. was keeping", "d. had been kept", "43) By the time Nada arrived, we ....... lunch, so there was nothing for her to eat.", "a. had had", "b. were having", "c. have had", "d. had", "44) By the time we....... the report, we’ll do the experiment.", "a. would write ", "b. had written ", "c. wrote ", "d. have written", "45) Could you put another piece of wood on the fire, please, before it goes ....... ?", "a. down", "b. on", "c. up", "d. out", "46) Did you like the new 'James Bond' movie? - No, I ....... it yet.", "a. don't", "b. haven't seen", "c. did not see", "d. hadn't seen", "47) Did you like the new 'James Bond' movie? - Yes, I ....... two weeks ago.", "a. have seen", "b. saw", "c. see", "d. had seen", "48) Different cultures have different ways of ....... their children.", "a. disciplining", "b. describing", "c. inscribing", "d. description", "49) Do you have any trouble with Shakespeare's writing .......?", "a. stem", "b. still", "c. steel", "d. style", "50) Do you know what time Jana ....... the office?", "a. left", "b. has left", "c. leave", "d. are leaving");
                    ArrayList<String> qs2 = new ArrayList<>();
                    ArrayList<String> a1s2 = new ArrayList<>();
                    ArrayList<String> a2s2 = new ArrayList<>();
                    ArrayList<String> a3s2 = new ArrayList<>();
                    ArrayList<String> a4s2 = new ArrayList<>();

                    addExam2(strings2, a1s2, a2s2, a3s2, a4s2, ras2, qs2);

                    break;
                }
                case "R2": {
                    String[] ras2 =
                            {"d", "b", "b", "d", "d", "a", "b", "c", "d", "c", "a", "d", "c", "d", "a", "b", "d", "c", "c", "a", "a", "c", "d", "d", "c", "b", "d", "d", "d", "b", "d", "d", "c", "b", "b", "a", "a", "b", "b", "b", "c", "b", "d", "c", "b", "c", "a", "a", "c", "c",};
                    List<String> strings2 = Arrays.asList("51) Do you mind if I give you some ....... advice?", "a. father", "b. farther", "c. further", "d. fatherly", "52) Doesn't this room look better? I....... some posters up on the walls.", "a. put ", "b. have put ", "c. am putting ", "d. will put", "53) Dr Magdi Yaqoub is one of the ....... of heart surgery.", "a. biochemists", "b. pioneers", "c. biologists", "d. engineers", "54) Jana didn’t send the message to her father until she ....... it.", "a. has been writing", "b. writing", "c. has written", "d. had written", "55) Famous people can be ....... with a special degree from this university.", "a. denied", "b. accused", "c. punished", "d. honoured", "56) Frank ....... for ABC Electric from 1990 to 2002.", "a. worked ", "b. had worked ", "c. is working ", "d. works", "57) Great works of English....... are studied in universities all over the world.", "a. picture", "b. literature", "c. treasure", "d. measure", "58) Hady ....... at the bank for three years when he was young.", "a. works", "b. is working", "c. has worked", "d. worked", "59) Hardly ....... from abroad when he was asked to travel again.", "a. he has returned", "b. has he returned", "c. he had returned", "d. had he returned", "60) Have you cleaned the flat .......? You' re so quick and efficient.", "a. ever ", "b. yesterday", "c. already ", "d. never", "61) Have you ever ....... Chinese food?", "a. eaten", "b. eats", "c. ate", "d. to eat", "62) Haven't you finished your food .......? No, I am still eating mom.", "a. never", "b. ever", "c. just", "d. yet", "63) Having ....... the shopping, mother started to prepare lunch.", "a. do", "b. she did", "c. done", "d. she does", "64) He gets angry if anyone discusses his religious .......", "a. believe ", "b. believers", "c. believes", "d. beliefs", "65) He got into the class ....... he had seen the teacher coming.", "a. when ", "b. before ", "c. till ", "d. having", "66) He had a slight....... in his back from lifting boxes all day.", "a. pulse", "b. ache", "c. painful", "d. ash", "67) He had no previous ....... of managing a firm.", "a. expiry", "b. extraction", "c. experiment", "d. experience", "68) He had no sooner seen his father ....... he went to his bedroom.", "a. or", "b. that", "c. than", "d. when", "69) He joined the navy after ....... from high school.", "a. grading", "b. marking", "c. graduating", "d. going", "70) He published a ....... of short stories called Music in my Life'.", "a. collection", "b. prediction", "c. conviction", "d. production", "71) He was a little boy, but he ....... as if he was an adult.", "a. behaved", "b. paved", "c. believed", "d. behold", "72) He was forced to ....... early because of poor health.", "a. acquire", "b. require", "c. retire", "d. inquire", "73) He....... for two weeks. He is trying to give it up.", "a. don't smoke ", "b. didn't smoke ", "c. does not smoke", "d. hasn't smoked", "74) Hello! I've just ....... to the shops. I've bought lots of things.", "a. gone", "b. been going", "c. being", "d. been", "75) Her career as a ....... has brought her enormous prestige.", "a. fire fighter", "b. dancer", "c. diplomat", "d. maid", "76) He's working for the ministry of finance now. He ....... loads of money.", "a. borrows", "b. makes", "c. wastes", "d. invents", "77) He's....... well in his new job, so the manager decided to promote him.", "a. studying", "b. having", "c. making", "d. doing", "78) His feet were ....... from standing so long.", "a. cooking", "b. licking", "c. baking", "d. aching", "79) His health problems may have had some....... on his decision.", "a. sequence", "b. affect", "c. flute", "d. influence", "80) How many meals.......so far today?", "a. did you prepared  ", "b. have you prepared  ", "c. do you prepare  ", "d. had you prepared", "81) I ....... a noise while I was studying last night.", "a. hearing", "b. heard", "c. was hearing", "d. heard", "82) I ....... already seen the film but I decided to watch it again last night.", "a. has", "b. did ", "c. have", "d. had", "83) I ....... for over five years now and I will never smoke again.", "a. don't smoke", "b. didn't smoke", "c. haven't smoked", "d. wasn't smoking", "84) I ....... French since I was at school.", "a. hadn’t studied ", "b. haven’t studied ", "c. didn’t study ", "d. don’t study", "85) I ....... my exams finally - I'm so happy!", "a. finished", "b. have finished", "c. had finished", "d. finish", "86) I ....... my keys - can you help me look for them?", "a. have lost", "b. had lost", "c. lost", "d. lose", "87) I ....... my last novel six times before I was happy with it.", "a. had changed", "b. have changed", "c. changed", "d. has changed", "88) I....quite a few financial problems last year but this year things are getting better.", "a. have had", "b. had", "c. had had", "d. have", "89) I ....... shopping until I had finished the housework.", "a. don't go", "b. didn't go", "c. won't go", "d. haven't gone", "90) I ....... this car for twenty years and it's as reliable as ever.", "a. have", "b. have had", "c. had", "d. have been having", "91) I ....... three cups of coffee today.", "a. had drunk", "b. drink", "c. have drunk ", "d. drinks", "92) I always have a....... break for coffee at midday.", "a. ten- minutes", "b. ten- minute", "c. ten minutes", "d. ten minute's", "93) I always let children do whatever they want, that's why they ....... badly.", "a. believe ", "b. become ", "c. respect ", "d. behave", "94) I can't believe that someone of his....... can believe in such lies!", "a. sensitive", "b. idiot", "c. intelligence", "d. intelligent", "95) I couldn't open the door as I ....... a shower.", "a. had had ", "b. was having ", "c. had ", "d. has had", "96) I couldn't phone my friend as I ....... my phone at home.", "a. was leaving ", "b. has left ", "c. had left ", "d. was left", "97) I enjoyed the book, but I can't remember the name of the.......", "a. author", "b. reader", "c. inventor", "d. discoverer", "98) I entered the office and looked around. Most people ....... at their office.", "a. were working", "b. worked", "c. had worked", "d. used to work", "99) I fell to the ground as I ....... for the bus.", "a. ran", "b. had run", "c. was running", "d. have run", "100) I find it hard to sleep unless there is complete .......", "a. silent", "b. silently", "c. silence", "d. silver");
                    ArrayList<String> qs2 = new ArrayList<>();
                    ArrayList<String> a1s2 = new ArrayList<>();
                    ArrayList<String> a2s2 = new ArrayList<>();
                    ArrayList<String> a3s2 = new ArrayList<>();
                    ArrayList<String> a4s2 = new ArrayList<>();

                    addExam2(strings2, a1s2, a2s2, a3s2, a4s2, ras2, qs2);

                    break;
                }
                case "R3": {
                    String[] ras2 =
                            {"d", "c", "c", "c", "a", "b", "a", "b", "d", "b", "b", "d", "a", "a", "d", "c", "c", "a", "a", "d", "c", "d", "d", "c", "b", "c", "a", "c", "b", "b", "c", "a", "c", "a", "c", "c", "c", "a", "c", "a", "d", "a", "c", "c", "b", "a", "c", "c", "d", "b"};
                    List<String> strings2 = Arrays.asList("101) Have you send us the new price list of your products.......? - We need it as soon as possible.", "a. since", "b. just", "c. ever", "d. yet", "102) I haven’t driven a car....... my childhood.", "a. ago ", "b. as soon as ", "c. since ", "d. for", "103) I haven't seen the film, so don't ....... it for me by telling me what happens.", "a. speck", "b. snail", "c. spoil", "d. spill", "104) I heard a knock at the front door so I ....... to answer it.", "a. had gone", "b. was going", "c. went", "d. had been", "105) I hope this success will ....... you to greater efforts.", "a. inspire", "b. inspect", "c. instinct", "d. instill", "106) I lost my house key last week and I ....... it yet.", "a. have found", "b. haven't found", "c. didn't found", "d. found", "107) I read the poem in a collection of modern.......", "a. poetry", "b. poet", "c. poetess", "d. poetical", "108) I suggested setting ....... an association to defend teachers' rights.", "a. on", "b. up", "c. into", "d. off", "109) I want to thank everyone who has ....... and supported me.", "a. courage", "b. cowardice ", "c. discouraged", "d. encouraged", "110) I was happy to see her, because we ....... each other for years.", "a. don't see", "b. had not seen", "c. not see", "d. cannot see", "111) I was having lunch when the telephone bell........", "a. was ringing", "b. rang", "c. ring", "d. had rung", "112) I was writing an e-mail when our computer ....... down.", "a. breaks", "b. has broken", "c. broken", "d. broke", "113) I’ll contact you as soon as he.......", "a. arrives ", "b. arrived ", "c. had arrived ", "d. would arrive", "114) I’ve written to them three times, but they ....... yet", "a. haven't replied", "b. didn't reply", "c. won't reply", "d. have replied", "115) I'd like to thank my parents for all of their love and ....... over the years.", "a. supervise", "b. supply", "c. suppose", "d. support", "116) If you study .......science at university. you can get a good job in an embassy.", "a. artificial", "b. surgical", "c. political", "d. natural", "117) If you want to quit smoking, you have to make a ....... effort.", "a. seriousness", "b. curious", "c. serious", "d. furious", "118) I'll send the document as an / a ....... to my next e-mail.", "a. attachment", "b. detachment", "c. improvement", "d. pavement", "119) I'm afraid the company is going bankrupt. It has had big problems.......", "a. lately", "b. ever", "c. just", "d. yet", "120) I'm busy right now. You can set up an appointment with my .......", "a. secretarial", "b. secret", "c. secrete", "d. secretary", "121) I'm totally ....... Could you explain that again?", "a. refused", "b. fused", "c. confused", "d. relaxed", "122) In my whole life, I .......never met a famous person.", "a. had", "b. has", "c. will have", "d. have", "123) In the first chapter, the ....... discusses childcare issues.", "a. reader", "b. publisher", "c. printer", "d. author", "124) Is Jana coming to the cinema with us? - No, she....... the film.", "a. already see", "b. is already seeing", "c. had already seen", "d. already had seen", "125) It is ....... to eat coloured eggs on Sham Elneseem.", "a. peculiar", "b. traditional", "c. odd", "d. unusual", "126) It is against the....... to park on a double yellow line.", "a. game", "b. ruler", "c. law", "d. lawyer", "127) It is important to look at the political and...context in which the novel was written.", "a. cultural", "b. cultured", "c. culture", "d. culturally", "128) It is the ....... for the bride to wear a white dress on her wedding day.", "a. habit", "b. costume", "c. custom", "d. traditional", "129) It is their ....... to thank the host before they start eating.", "a. traditional", "b. tradition", "c. trade", "d. trader", "130) It rained a lot last week, but it ....... much so far this week.", "a. didn't rain", "b. hasn't rained", "c. hadn't rained", "d. doesn't rain", "131) It remains....... to own these guns but not to carry them in public.", "a. awful", "b. careful", "c. lawful", "d. eventful", "132) It was raining heavily, so I .......at home.", "a. stayed", "b. have stayed", "c. has stayed", "d. was stayed", "133) It's a long time ....... I last saw you.", "a. until", "b. after", "c. since", "d. when", "134) It's a serious disease that can cause ....... or death.", "a. disability", "b. ability", "c. treatment", "d. energy", "135) I've set up a small company that....... only about four books a year.", "a. lends", "b. sells", "c. publishes", "d. writes", "136) Last night I ....... my keys - I had to call my flat mate to let me in.", "a. have lost", "b. had lost", "c. lost", "d. lose", "137) Liverpool are playing with a lot of........ I think they are going to win!", "a. confident ", "b. doubt ", "c. confidence", "d. dependence", "138) Local officials are reminding people to obey the ....... and not sell fireworks to children under sixteen.", "a. law", "b. low", "c. row", "d. raw", "139) Local people complained that the new office building would...the view of the city.", "a. mend ", "b. repair ", "c. spoil ", "d. fix", "140) Magdy ....... into this apartment in 2005.", "a. moved", "b. will move", "c. has moved", "d. was moving", "141) Maha offered me another drink but I refused as I ....... enough.", "a. was having", "b. have had", "c. had", "d. had had", "142) Maha's grandparents give him everything he wants; they ....... her.", "a. spoil", "b. spell", "c. spill", "d. soil", "143) Maintaining classroom....... is the first task of every teacher.", "a. discrimination", "b. pollution", "c. discipline", "d. noise", "144) Managing my finances can ....... me a real headache.", "a. make", "b. get", "c. give", "d. prove", "145) Many fans were surprised by the great player's.......", "a. improvement", "b. retirement", "c. astonishment", "d. unemployment", "146) Mohammed Salah quickly ....... himself as a great footballer.", "a. established", "b. polished", "c. published", "d. denied", "147) Mohammed Salah....... many young people to take up football.", "a. charged", "b. instead", "c. inspired", "d. expired", "148) Jana....... lunch when her friend phoned her, so she couldn't answer the phone.", "a. having", "b. had", "c. was having", "d. had had", "149) Jana is certain to win the race; there's just no.......", "a. compete", "b. competitors", "c. competitive", "d. competition", "150) Most students spend about a quarter of their time listening to....... at collage.", "a. pressures", "b. lectures", "c. measures", "d. noise");
                    ArrayList<String> qs2 = new ArrayList<>();
                    ArrayList<String> a1s2 = new ArrayList<>();
                    ArrayList<String> a2s2 = new ArrayList<>();
                    ArrayList<String> a3s2 = new ArrayList<>();
                    ArrayList<String> a4s2 = new ArrayList<>();

                    addExam2(strings2, a1s2, a2s2, a3s2, a4s2, ras2, qs2);

                    break;
                }
                case "R4": {
                    String[] ras2 = {"b", "b", "d", "c", "c", "c", "b", "b", "c", "a", "b", "c", "c", "a", "b", "c", "d", "c", "c", "b", "a", "a", "b", "b", "d", "c", "b", "d", "b", "c", "b", "c", "a", "b", "b", "b", "c", "b", "c", "a", "c", "b", "a", "a", "d", "b", "a", "b", "a", "a"};
                    List<String> strings2 = Arrays.asList("151) Mr Ali's ....... of management is remarkable. All his employees admire him.", "a. tile", "b. style", "c. tyre", "d. liar", "152) Mr. Ali ....... in a bank for 15 years. Then he gave it up.", "a. has worked", "b. worked", "c. will work", "d. works", "153) Mum was a great ....... in herbal medicines. She is all for them.", "a. believe", "b. belief", "c. disbeliever", "d. believer", "154) My ....... as an English teacher didn't last long.", "a. care", "b. core", "c. career", "d. coal", "155) My aunt bought me a lovely gift after ....... the summer holiday with us.", "a. have spent", "b. spent", "c. spending", "d. had spent", "156) My brother ate all of the cake that our mum.......", "a. is making", "b. will make", "c. had made", "d. make", "157) My daughter is always wasting her time ....... with her online friends.", "a. delivering", "b. chatting", "c. designing", "d. eating", "158) My elder brother ....... my decision to become a doctor like him.", "a. affect ", "b. influenced ", "c. influential ", "d. effect", "159) My father caught me and gave me a long....... about the dangers of smoking.", "a. criticise", "b. debate", "c. lecture", "d. period", "160) My father's car was hit by a driver who ....... at mad speed.", "a. was driving", "b. drive", "c. was driven", "d. drives", "161) My glasses .......broken. I can’t see well.", "a. was ", "b. have been ", "c. is ", "d. had been", "162) My handwriting is bad. What advice can you ....... me?", "a. make", "b. offer", "c. give", "d. hand", "163) Mrs. Jana is my present teacher . I....... in her class for three months now.", "a. am", "b. was", "c. have been", "d. had been", "164) My parents had a nice .......about what their kids were doing.", "a. chat ", "b. cheat ", "c. chin ", "d. shot", "165) My parents have been the most ....... people in my life.", "a. inflammable", "b. influential", "c. influence", "d. influenza", "166) My parents were very ....... with me when I was young.", "a. restrict", "b. instinct", "c. strict", "d. strictness", "167) My sister ....... in Canada before she moved on to London", "a. has lived", "b. was living", "c. is living", "d. had lived", "168) My sister has lived in Luxor since he....... 30.", "a. is ", "b. has been ", "c. was ", "d. had been", "169) My uncle got a job as a / an ....... at the local college.", "a. lecture ", "b. assistant ", "c. lecturer ", "d. cooker", "170) No sooner ....... the noise than we rushed to the spot.", "a. we did hear", "b. had we heard", "c. we had heard", "d. we hear", "171) Nurseries are ....... for the children in their care.", "a. responsible", "b. response", "c. irresponsible", "d. responsibility", "172) On ....... the good news, Ahmed phoned his parents.", "a. hearing", "b. hear", "c. heard", "d. had heard", "173) On seeing the fire, she ....... for help.", "a. had shouted", "b. shouted", "c. was shouted", "d. . have shouted", "174) On the seventh day of the month, the moon looks like a.......", "a. triangle", "b. semicircle", "c. circle", "d. square", "175) Only very few buildings could survive the....... as it was destructive.", "a. earring", "b. silkworm", "c. earthworm", "d. earthquake", "176) Our meeting wasn't ....... . It was completely accidental.", "a. pinned", "b. placed", "c. planned", "d. planted", "177) Our neighbor brought cake ....... juice for everyone.", "a. as far as", "b. as well as", "c. as long as", "d. as well", "178) Our teacher ....... more than a dozen exam papers up to now.", "a. checks", "b. check", "c. was checking", "d. has checked", "179) Pages of this book should not be copied without the permission of the .......", "a. vet", "b. publisher", "c. barber", "d. polisher", "180) Reading literature introduces us to other people’s experiences and.......", "a. capture", "b. cultural", "c. cultures", "d. culturally", "181) Ali didn't want to go to the party, but Jana managed to ....... him.", "a. persuasion", "b. persuade", "c. persuasive", "d. persist", "182) Jana....... how to swim three years ago.", "a. will learned", "b. had learned", "c. learned", "d. have learned", "183) Jana....... ill when I visited her.", "a. seemed", "b. has seemed", "c. was seeming", "d. seems", "184) She ....... in seven different countries, so she knows a lot about different cultures.", "a. live", "b. has lived ", "c. had lived", "d. lived", "185) She arrived to the cinema late. The movie ....... twenty minutes earlier.", "a. was beginning", "b. had begun", "c. has begun", "d. begin", "186) She claims that her personal problems had no....... upon her decision to resign.", "a. affect", "b. influence", "c. sequence", "d. fluency", "187) She felt ill last night because she ....... too much the night before.", "a. has eaten", "b. was eating", "c. had eaten", "d. eats", "188) She felt relaxed after six days of .......in the countryside.", "a. noise", "b. silence", "c. silent", "d. madness", "189) She hasn’t been to Cairo .......many years.", "a. ago ", "b. since ", "c. for ", "d. when", "190) She hasn’t spoken to me....... last week.", "a. since", "b. for", "c. ever", "d. already", "191) She hasn’t spoken to me.......2 weeks.", "a. already", "b. since", "c. for ", "d. recently", "192) She left at ....... to go a shopping centre and has not been seen ever since.", "a. all day", "b. midday", "c. today", "d. daydream", "193) She spent eight years ....... in New York City.", "a. living", "b. to live", "c. lived", "d. live", "194) She stayed up all night ....... with her friends online.", "a. chatting", "b. cheating", "c. punching ", "d. pinching", "195) She studied several languages in college, hoping some day to work for the foreign .......", "a. minister", "b. chemistry", "c. dentistry", "d. ministry", "196) She wanted to work for a bigger and more....... newspaper.", "a. inflate", "b. influential", "c. influence", "d. inflation", "197) She was late because she ....... to set her alarm clock.", "a. had forgotten", "b. has forgotten", "c. was forgetting", "d. forget", "198) She wore the shoes she ....... the previous day.", "a. have bought", "b. 'd bought", "c. will buy", "d. buy", "199) Jana....... from school in 2001.", "a. graduated", "b. graduate", "c. graduates", "d. had graduated", "200) Jana.......some very helpful suggestions but her boss rejected them all.", "a. made", "b. did", "c. refused", "d. denied");
                    ArrayList<String> qs2 = new ArrayList<>();
                    ArrayList<String> a1s2 = new ArrayList<>();
                    ArrayList<String> a2s2 = new ArrayList<>();
                    ArrayList<String> a3s2 = new ArrayList<>();
                    ArrayList<String> a4s2 = new ArrayList<>();

                    addExam2(strings2, a1s2, a2s2, a3s2, a4s2, ras2, qs2);

                    break;
                }
            }
        }

        if (examList.isEmpty())
            backFlag = 0;
        else {
            backFlag = 1;
            for (Exam exam : examList) {
                stuAnswersList.add("لم يتم الاجابة عليه");
            }
        }
        examSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answers = "";
                for (int i = 0; i < examList.size(); i++) {
                    if (stuAnswersList.get(i).trim().equals(examList.get(i).getReal_answer().trim())) {
                        mark++;
                    } else {
                        answers += "\n" + examList.get(i).getQuestion() + " ( " + examList.get(i).getReal_answer() + " )"
                                + "\n" + "اجابتك الخاطئة: " + stuAnswersList.get(i) + "\n";
                    }
                }
                backFlag = 0;
                markTv.setVisibility(View.VISIBLE);
                eradioGroupAll.setVisibility(View.GONE);
                examSubmitBtn.setVisibility(View.GONE);
                backBtn.setVisibility(View.GONE);
                nextBtn.setVisibility(View.GONE);
                counterTv.setText("SOLVED");
                questionsProgress.setVisibility(View.GONE);
                questionsProgress.setMax(examList.size());
                questionsProgress.setProgress(x);
                markTv.setText(mark + "/" + examList.size());
                equestionTv.setText(answers);
                SharedPreferences.Editor editor = answersPreferences.edit();
                editor.putString("answers" + testName, answers);
                editor.putString("marks" + testName, mark + "/" + examList.size());
                editor.apply();
                editor.commit();
            }
        });

//        eradioGroupAll.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                examSubmitBtn.setEnabled(true);
//            }
//        });
    }

    public void addExam(List<String> strings, ArrayList<String> a1s, ArrayList<String> a2s, ArrayList<String> a3s, ArrayList<String> a4s, String[] ras, String[] qs) {
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i).trim();

            if (s.startsWith("a.")) {
                a1s.add(s);
            } else if (s.startsWith("b.")) {
                a2s.add(s);
            } else if (s.startsWith("c.")) {
                a3s.add(s);
            } else if (s.startsWith("d.")) {
                a4s.add(s);
            }
        }
        for (int i = 0; i < a1s.size(); i++) {
            String get = ras[i].trim();
            if (a1s.get(i).startsWith(get)) {
                ras[i] = a1s.get(i);
            } else if (a2s.get(i).startsWith(get)) {
                ras[i] = a2s.get(i);
            } else if (a3s.get(i).startsWith(get)) {
                ras[i] = a3s.get(i);
            } else if (a4s.get(i).startsWith(get)) {
                ras[i] = a4s.get(i);
            }

        }

        for (int i = 0; i < a1s.size(); i++) {
            String q = qs[i].trim(),
                    a1 = a1s.get(i).trim(),
                    a2 = a2s.get(i).trim(),
                    a3 = a3s.get(i).trim(),
                    a4 = a4s.get(i).trim(),
                    ra = ras[i].trim();
            examList.add(new Exam(q, a1, a2, a3, a4, ra));
        }
    }

    private void addExam2(List<String> strings1, ArrayList<String> a1s1, ArrayList<String> a2s1, ArrayList<String> a3s1, ArrayList<String> a4s1, String[] ras1, ArrayList<String> qs1) {
        for (int i = 0; i < strings1.size(); i++) {
            String ra = strings1.get(i).trim();
            char c = ra.toLowerCase().charAt(0);
            if (Character.isDigit(c)) {
                qs1.add(ra);
            } else if (c == 'a') {
                a1s1.add(ra);
            } else if (c == 'b') {
                a2s1.add(ra);
            } else if (c == 'c') {
                a3s1.add(ra);
            } else if (c == 'd') {
                a4s1.add(ra);
            }
        }
        for (int i = 0; i < a1s1.size(); i++) {
            String get = ras1[i].trim();
            if (a1s1.get(i).startsWith(get)) {
                ras1[i] = a1s1.get(i);
            } else if (a2s1.get(i).startsWith(get)) {
                ras1[i] = a2s1.get(i);
            } else if (a3s1.get(i).startsWith(get)) {
                ras1[i] = a3s1.get(i);
            } else if (a4s1.get(i).startsWith(get)) {
                ras1[i] = a4s1.get(i);
            }
        }
        for (int i = 0; i < qs1.size(); i++) {
            String q = qs1.get(i).trim(),
                    a1 = a1s1.get(i).trim(),
                    a2 = a2s1.get(i).trim(),
                    a3 = a3s1.get(i).trim(),
                    a4 = a4s1.get(i).trim(),
                    ra = ras1[i].trim();
            examList.add(new Exam(q, a1, a2, a3, a4, ra));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (getIntent().getExtras().getString("type").equals("online"))
        {
            questionsProgress.setVisibility(View.GONE);
            database = FirebaseDatabase.getInstance();
            examRef = database.getReference("Test Exam");
            examRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot n : snapshot.getChildren()) {
                        Exam exam = n.getValue(Exam.class);
                        examList.add(exam);
                    }
                    for (Exam exam : examList) {
                        stuAnswersList.add("لم يتم الاجابة عليه");
                    }
                    if (examList.size() > 0 && !(MR >= examList.size())) {
                        backFlag = 0;
                        getQuestions(examList, MR);
                        counterTv.setText(x + "/" + examList.size());
                        backBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                nextBtn.setEnabled(true);
                                int radioId = eradioGroupAll.getCheckedRadioButtonId();
                                RadioButton radioButton = findViewById(radioId);
                                if (radioButton != null)
                                    stuAnswersList.set(MR, radioButton.getText().toString());
                                MR--;
                                if (MR == 0)
                                    backBtn.setEnabled(false);
                                x = MR + 1;
                                counterTv.setText(x + "/" + examList.size());
                                getQuestions(examList, MR);
                            }
                        });

                        nextBtn.setOnClickListener(v -> {
                            backBtn.setEnabled(true);
                            showMarksBtn.setVisibility(View.GONE);

                            int radioId = eradioGroupAll.getCheckedRadioButtonId();
                            RadioButton radioButton = findViewById(radioId);
//                        String s = radioButton.getText().toString().toLowerCase();

                            if (radioButton != null)
                                stuAnswersList.set(MR, radioButton.getText().toString());
                            MR++;
                            if (MR >= examList.size()) {

                                MR--;
                                nextBtn.setEnabled(false);
                                examSubmitBtn.setEnabled(true);
                                examSubmitBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        backFlag = 0;
                                        answers = "";
                                        for (int i = 0; i < examList.size(); i++) {
                                            if (stuAnswersList.get(i).trim().equals(examList.get(i).getReal_answer().trim())) {
                                                mark++;
                                            } else {
                                                answers += "\n" + examList.get(i).getQuestion() + " ( " + examList.get(i).getReal_answer() + " )"
                                                        + "\n" + "اجابتك الخاطئة: " + stuAnswersList.get(i) + "\n";
                                            }
                                        }
                                        markTv.setVisibility(View.VISIBLE);
                                        eradioGroupAll.setVisibility(View.GONE);
                                        examSubmitBtn.setVisibility(View.GONE);
                                        backBtn.setVisibility(View.GONE);
                                        nextBtn.setVisibility(View.GONE);
                                        counterTv.setText("SOLVED");
                                        markTv.setText(mark + "/" + examList.size());
                                        equestionTv.setText(answers);


                                    }
                                });
                            } else {
                                x = MR + 1;
                                counterTv.setText(x + "/" + examList.size());
                                getQuestions(examList, MR);
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        if (!answersString.equals("MR")) {

            showMarksBtn.setVisibility(View.VISIBLE);
            showMarksBtn.setOnClickListener(v -> setSolved());
        }

        if (examList.size() > 0 && !(MR >= examList.size())) {
            backFlag = 0;
            getQuestions(examList, MR);
            counterTv.setText("Question " + x + "/" + examList.size());
            questionsProgress.setMax(examList.size());
            questionsProgress.setProgress(x);
            questionsProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (progress == 1)
                        backBtn.setEnabled(false);
                    else
                        backBtn.setEnabled(true);

                    if (progress == examList.size()) {
                        examSubmitBtn.setEnabled(true);
                        nextBtn.setEnabled(false);
                    } else
                        nextBtn.setEnabled(true);
                    x = progress;
                    MR = progress - 1;
                    counterTv.setText("Question " + progress + "/" + examList.size());
                    getQuestions(examList, progress - 1);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nextBtn.setEnabled(true);
                    int radioId = eradioGroupAll.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(radioId);
                    if (radioButton != null)
                        stuAnswersList.set(MR, radioButton.getText().toString());
                    MR--;
                    if (MR == 0)
                        backBtn.setEnabled(false);
                    x = MR + 1;
                    counterTv.setText("Question " + x + "/" + examList.size());
                    questionsProgress.setMax(examList.size());
                    questionsProgress.setProgress(x);
                    getQuestions(examList, MR);
                }
            });
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backBtn.setEnabled(true);
                    showMarksBtn.setVisibility(View.GONE);
                    int radioId = eradioGroupAll.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(radioId);
                    if (radioButton != null)
                        stuAnswersList.set(MR, radioButton.getText().toString());
//                    if (radioButton.getText().toString().trim().equals(examList.get(MR).getReal_answer().trim())) {
//                        mark++;
//                    } else {
//                        answers += "\n" + examList.get(MR).getQuestion() + " ( " + examList.get(MR).getReal_answer() + " )"
//                                + "\n" + "اجابتك الخاطئة: " + radioButton.getText() + "\n";
//                    }
                    MR++;
                    if (MR >= examList.size()) {
                        MR--;
                        nextBtn.setEnabled(false);
                        examSubmitBtn.setEnabled(true);

                    } else {
                        backFlag = 1;
                        x = MR + 1;
                        counterTv.setText("Question " + x + "/" + examList.size());
                        questionsProgress.setMax(examList.size());
                        questionsProgress.setProgress(x);
                        getQuestions(examList, MR);
                    }
                }
            });
        }
//        else if (examList.isEmpty()) {
//            timerTv.setVisibility(View.GONE);
//            examTitleTv.setText("سيتم اضافة باقي الامتحانات قريبًأ :)");
//            questionsLl.setVisibility(View.GONE);
//        }

    }

    private void setSolved() {
        backFlag = 0;
        markTv.setVisibility(View.VISIBLE);
        eradioGroupAll.setVisibility(View.GONE);
        backBtn.setVisibility(View.GONE);
        nextBtn.setVisibility(View.GONE);
        examSubmitBtn.setVisibility(View.GONE);
        timerTv.setVisibility(View.GONE);
        equestionTv.setText(answersString);
        counterTv.setText("SOLVED");
        questionsProgress.setVisibility(View.GONE);
        markTv.setText(marksString);
        showMarksBtn.setVisibility(View.GONE);
    }

    private void getQuestions(ArrayList<Exam> examArrayList, int mr) {
        equestionTv.setText(examArrayList.get(mr).getQuestion());
        eradioOne.setText(examArrayList.get(mr).getAns1());
        eradioTwo.setText(examArrayList.get(mr).getAns2());
        eradioThree.setText(examArrayList.get(mr).getAns3());
        eradioFour.setText(examArrayList.get(mr).getAns4());
        eradioGroupAll.clearCheck();

        String chosen = stuAnswersList.get(mr);
        if (!chosen.equals("لم يتم الاجابة عليه")) {
            if (eradioOne.getText().toString().equals(chosen)) {
                eradioOne.setChecked(true);
            } else if (eradioTwo.getText().toString().equals(chosen)) {
                eradioTwo.setChecked(true);
            } else if (eradioThree.getText().toString().equals(chosen)) {
                eradioThree.setChecked(true);
            } else if (eradioFour.getText().toString().equals(chosen)) {
                eradioFour.setChecked(true);
            } else {
                eradioGroupAll.clearCheck();
            }
        }
    }


    @Override
    public void onBackPressed() {
        openDialog();


//        if (backFlag == 0) {
//            MR = 0;
//            x = 0;
//            mark = 0;
//            answers = "";
//            examList.clear();
//            super.onBackPressed();
//        } else {
//            int flag = 0;
//            for (String s : stuAnswersList) {
//                if (!s.equals("لم يتم الاجابة عليه")) {
//                    flag = 1;
//                }
//            }
//            if (flag == 1) {
//                openDialog();
//            } else {
//                super.onBackPressed();
//            }
//        }
    }

    private void openDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.exam_alert, null);
        dialog.setView(view);
        Button exit = view.findViewById(R.id.exit_btn);
        Button cancel = view.findViewById(R.id.cancel_btn);

        final AlertDialog alertDialog = dialog.create();
        alertDialog.show();
        exit.setOnClickListener(v -> {
            MR = 0;
            x = 0;
            mark = 0;
            answers = "";
            examList.clear();
            super.onBackPressed();
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
    }

}
