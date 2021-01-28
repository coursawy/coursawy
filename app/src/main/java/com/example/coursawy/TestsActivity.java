package com.example.coursawy;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coursawy.cam.FaceDetectionCamera;
import com.example.coursawy.cam.FrontCameraRetriever;
import com.example.coursawy.model.Exam;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestsActivity extends AppCompatActivity implements FrontCameraRetriever.Listener, FaceDetectionCamera.Listener {
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

    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    Camera camera;
    private String testName;
    private ArrayList<Exam> examList;
    String answersString = "";
    String marksString = "";
    public static String answers = "";

    SharedPreferences answersPreferences;
    public static int MR = 0, mark = 0;
    private int x = MR + 1;
    ArrayList<String> stuAnswersList;


    DatabaseReference examRef;
    FirebaseDatabase database;

    CountDownTimer countDownTimer;
    private long timeLiftInMilliSeconds = 60000 * 60;
    private boolean timerFlag = true;
    private boolean timeOver = false;
    ShowCamera showCamera;
    private static final int MY_CAMERA_REQUEST_CODE = 100;

    private static final String TAG = "FDT" + MainActivity.class.getSimpleName();
    private TextView detectionTv;
    AlertDialog.Builder dialog;
    View warningView;
    AlertDialog warningDialog;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);
        ButterKnife.bind(this);

        detectionTv = findViewById(R.id.detection_tv);
        FrontCameraRetriever.retrieveFor(this);

        dialog = new AlertDialog.Builder(this);
        warningView = getLayoutInflater().inflate(R.layout.exam_warning, null);
        dialog.setView(warningView);
        warningDialog = dialog.create();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
            }
//            else {
//                camera = Camera.open();
//                showCamera = new ShowCamera(this, camera);
//                frameLayout.addView(showCamera);
//            }
        }
        answersPreferences = getPreferences(MODE_PRIVATE);
        stuAnswersList = new ArrayList<>();
        questionsProgress.setProgress(x);

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

        examList = new ArrayList<>();

        if (getIntent().getExtras().getString("type").equals("offline")) {

            switch (testId) {
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
            }
        }

        if (!examList.isEmpty()) {
            for (Exam ignored : examList) {
                stuAnswersList.add("لم يتم الاجابة عليه");
            }
            displayQuestions();
        }
        examSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswered(MR);
                answers = "";
                mark = 0;
                for (int i = 0; i < examList.size(); i++) {
                    if (stuAnswersList.get(i).trim().equals(examList.get(i).getReal_answer().trim())) {
                        mark++;
                    } else {
                        answers += "\n" + examList.get(i).getQuestion() + " ( " + examList.get(i).getReal_answer() + " )"
                                + "\n" + "اجابتك الخاطئة: " + stuAnswersList.get(i) + "\n";
                    }
                }
                SharedPreferences.Editor editor = answersPreferences.edit();
                editor.putString("answers" + testName, answers);
                editor.putString("marks" + testName, mark + "/" + examList.size());
                editor.apply();
                editor.commit();
                Intent i = new Intent(TestsActivity.this, AnswersActivity.class);
                i.putExtra("answers", answers);
                i.putExtra("marks", mark + "/" + examList.size());
                i.putExtra("title", testName);
                startActivity(i);
            }
        });
    }

    private void checkAnswered(int MR) {
        int radioId = eradioGroupAll.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioId);
        if (radioButton != null)
            stuAnswersList.set(MR, radioButton.getText().toString());
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


    @Override
    protected void onStart() {
        super.onStart();
        if (!answersString.equals("MR")) {
            showMarksBtn.setVisibility(View.VISIBLE);
            showMarksBtn.setOnClickListener(v -> setSolved());
        }

        if (getIntent().getExtras().getString("type").equals("online")) {

//            if (!isInternetConnected()) {
//                timerTv.setVisibility(View.GONE);
//                examTitleTv.setText("يرجى التأكد من الاتصال بالإنترنت وإعادة المحاولة");
//                questionsLl.setVisibility(View.GONE);
//            }

            database = FirebaseDatabase.getInstance();
            examRef = database.getReference("Test Exam");
            examRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (examList.isEmpty()) {
                        for (DataSnapshot n : snapshot.getChildren()) {
                            Exam exam = n.getValue(Exam.class);
                            examList.add(exam);
                        }
                        for (Exam ignored : examList) {
                            stuAnswersList.add("لم يتم الاجابة عليه");
                        }
                    }
                    displayQuestions();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }

    private void displayQuestions() {
        if (!answersString.equals("MR")) {
            showMarksBtn.setVisibility(View.VISIBLE);
            showMarksBtn.setOnClickListener(v -> setSolved());
        }

        if (examList.size() > 0 && !(MR >= examList.size())) {
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
                    counterTv.setText(progress + "/" + examList.size());
                    getQuestions(examList, progress - 1);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    checkAnswered(MR);
                    if (timerFlag) {
                        timerFlag = false;
                        setTimer(examList.size());
                        startTimer();
                    }
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nextBtn.setEnabled(true);
                    checkAnswered(MR);
                    if (timeOver)
                        setSolvedTime();
                    else {
                        MR--;
                        if (MR == 0)
                            backBtn.setEnabled(false);
                        x = MR + 1;
                        counterTv.setText(x + "/" + examList.size());
                        questionsProgress.setProgress(x);
                        getQuestions(examList, MR);
                    }
                }
            });
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backBtn.setEnabled(true);
                    if (timerFlag) {
                        timerFlag = false;
                        setTimer(examList.size());
                        startTimer();
                    }

                    showMarksBtn.setVisibility(View.GONE);
                    checkAnswered(MR);
                    MR++;
                    if (timeOver)
                        setSolvedTime();
                    else {
                        x = MR + 1;
                        counterTv.setText(x + "/" + examList.size());
                        questionsProgress.setProgress(x);
                        getQuestions(examList, MR);
                    }

                    if (MR >= examList.size() - 1) {
                        nextBtn.setEnabled(false);
                        examSubmitBtn.setEnabled(true);
                    }
                }
            });
        }
    }

    private void setTimer(int size) {
        timeLiftInMilliSeconds = 60000 * (size);
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLiftInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLiftInMilliSeconds = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                timeOver = true;
            }
        }.start();
    }

    private void updateTimer() {
        int minutes = (int) timeLiftInMilliSeconds / 60000;
        int seconds = (int) timeLiftInMilliSeconds % 60000 / 1000;
        String timeLeft;
        timeLeft = "" + minutes;
        timeLeft += ":";
        if (seconds < 10)
            timeLeft += "0";
        timeLeft += seconds;
        timerTv.setText(timeLeft);
    }

    private void setSolved() {
        Intent i = new Intent(TestsActivity.this, AnswersActivity.class);
        i.putExtra("answers", answersString);
        i.putExtra("marks", marksString);
        i.putExtra("title", testName);
        startActivity(i);
    }

    private void setSolvedTime() {
        answers = "";
        mark = 0;
        for (int i = 0; i < examList.size(); i++) {
            if (stuAnswersList.get(i).trim().equals(examList.get(i).getReal_answer().trim())) {
                mark++;
            } else {
                answers += "\n" + examList.get(i).getQuestion() + " ( " + examList.get(i).getReal_answer() + " )"
                        + "\n" + "اجابتك الخاطئة: " + stuAnswersList.get(i) + "\n";
            }
        }
        answersString = answers;
        marksString = mark + "/" + examList.size();
        markTv.setVisibility(View.VISIBLE);
        eradioGroupAll.setVisibility(View.GONE);
        backBtn.setVisibility(View.GONE);
        nextBtn.setVisibility(View.GONE);
        examSubmitBtn.setVisibility(View.GONE);
        timerTv.setVisibility(View.GONE);
        equestionTv.setText(answersString);
        counterTv.setText("SOLVED");
        markTv.setText(marksString);
        showMarksBtn.setVisibility(View.GONE);
        questionsProgress.setVisibility(View.GONE);

        SharedPreferences.Editor editor = answersPreferences.edit();
        editor.putString("answers" + testName, answers);
        editor.putString("marks" + testName, mark + "/" + examList.size());
        editor.apply();
        editor.commit();
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
        if (!this.isFinishing()) {
            openDialog();
        }
    }

    private void openDialog() {
        AlertDialog.Builder exitDialog;
        exitDialog = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.exam_exit_alert, null);
        exitDialog.setView(view);
        Button exit = view.findViewById(R.id.exit_btn);
        Button cancel = view.findViewById(R.id.cancel_btn);

        AlertDialog alertDialog2 = exitDialog.create();
        if (!this.isFinishing()) {
            alertDialog2.show();
        }
        exit.setOnClickListener(v -> {
            MR = 0;
            x = 0;
            mark = 0;
            answers = "";
            examList.clear();
            if (alertDialog2 != null && alertDialog2.isShowing()) {
                alertDialog2.dismiss();
            }
            super.onBackPressed();
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog2.cancel();
                alertDialog2.dismiss();

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onFaceDetected() {
        if (!this.isFinishing()) {

            detectionTv.setText("شوفتك 👀");

            if (warningDialog != null && warningDialog.isShowing()) {
                warningDialog.cancel();
                warningDialog.dismiss();
            }
        }
    }

    @Override
    public void onFaceTimedOut() {
        if (!this.isFinishing()) {
            //        detectionTv.setText("مفيش حد هنا!!");
            detectionTv.setText("");

            if (!warningDialog.isShowing())
                warningDialog.show();
        }
    }

    @Override
    public void onFaceDetectionNonRecoverableError() {
        detectionTv.setText("Face detection error");

    }

    @Override
    public void onLoaded(FaceDetectionCamera camera) {
        SurfaceView cameraSurface = new CameraSurfaceView(this, camera, this);
        // Add the surface view (i.e. camera preview to our layout)
        ((FrameLayout) findViewById(R.id.frame_layout)).addView(cameraSurface);
    }

    @Override
    public void onFailedToLoadFaceDetectionCamera() {
        detectionTv.setText("Face detection error");
    }

    private boolean isInternetConnected() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
