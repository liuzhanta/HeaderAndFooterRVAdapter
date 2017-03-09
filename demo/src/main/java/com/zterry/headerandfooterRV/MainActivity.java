package com.zterry.headerandfooterRV;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.zterry.headerandfooteradapter_rv.BaseRecyclerViewAdapter;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String[] DATA_ARRAY = {
            "001天魁星呼保义宋江",
            "002天罡星玉麒麟卢俊义",
            "003天机星智多星吴用",
            "004天闲星入云龙公孙胜",
            "005天勇星大刀关胜",
            "006天雄星豹子头林冲",
            "007天猛星霹雳火秦明",
            "008天威星双鞭呼延灼",
            "009天英星小李广花荣",
            "010天贵星小旋风柴进",
            "011天富星扑天雕李应",
            "012天满星美髯公朱仝",
            "013天孤星花和尚鲁智深",
            "014天伤星行者武松",
            "015天立星双枪将董平",
            "016天捷星没羽箭张清",
            "017天暗星青面兽杨志",
            "018天佑星金枪手徐宁",
            "019天空星急先锋索超",
            "020天速星神行太保戴宗",
            "021天异星赤发鬼刘唐",
            "022天杀星黑旋风李逵",
            "023天微星九纹龙史进",
            "024天究星没遮拦穆弘",
            "025天退星插翅虎雷横",
            "026天寿星混江龙李俊",
            "027天剑星立地太岁阮小二",
            "028天竟星船火儿张横",
            "029天罪星短命二郎阮小五",
            "030天损星浪里白条张顺",
            "031天败星活阎罗阮小七",
            "032天牢星病关索杨雄",
            "033天慧星拚命三郎石秀",
            "034天暴星两头蛇解珍",
            "035天哭星双尾蝎解宝",
            "036天巧星浪子燕青",
            "037地魁星神机军师朱武",
            "038地煞星镇三山黄信",
            "039地勇星病尉迟孙立",
            "040地杰星丑郡马宣赞",
            "041地雄星井木犴郝思文",
            "042地威星百胜将韩滔",
            "043地英星天目将彭玘",
            "044地奇星圣水将单廷珪",
            "045地猛星神火将魏定国",
            "046地文星圣手书生萧让",
            "047地正星铁面孔目裴宣",
            "048地阔星摩云金翅欧鹏",
            "049地阖星火眼狻猊邓飞",
            "050地强星锦毛虎燕顺",
            "051地暗星锦豹子杨林",
            "052地轴星轰天雷凌振",
            "053地会星神算子蒋敬",
            "054地佐星小温侯吕方",
            "055地佑星赛仁贵郭盛",
            "056地灵星神医安道全",
            "057地兽星紫髯伯皇甫端",
            "058地微星矮脚虎王英",
            "059地慧星一丈青扈三娘",
            "060地暴星丧门神鲍旭",
            "061地然星混世魔王樊瑞",
            "062地猖星毛头星孔明",
            "063地狂星独火星孔亮",
            "064地飞星八臂哪吒项充",
            "065地走星飞天大圣李衮",
            "066地巧星玉臂匠金大坚",
            "067地明星铁笛仙马麟",
            "068地进星出洞蛟童威",
            "069地退星翻江蜃童猛",
            "070地满星玉幡竿孟康",
            "071地遂星通臂猿侯健",
            "072地周星跳涧虎陈达",
            "073地隐星白花蛇杨春",
            "074地异星白面郎君郑天寿",
            "075地理星九尾龟陶宗旺",
            "076地俊星铁扇子宋清",
            "077地乐星铁叫子乐和",
            "078地捷星花项虎龚旺",
            "079地速星中箭虎丁得孙",
            "080地镇星小遮拦穆春",
            "081地嵇星操刀鬼曹正",
            "082地魔星云里金刚宋万",
            "083地妖星摸着天杜迁",
            "084地幽星病大虫薛永",
            "085地伏星金眼彪施恩",
            "086地僻星打虎将李忠",
            "087地空星小霸王周通",
            "088地孤星金钱豹子汤隆",
            "089地全星鬼脸儿杜兴",
            "090地短星出林龙邹渊",
            "091地角星独角龙邹润",
            "092地囚星旱地忽律朱贵",
            "093地藏星笑面虎朱富",
            "094地平星铁臂膊蔡福",
            "095地损星一枝花蔡庆",
            "096地奴星催命判官李立",
            "097地察星青眼虎李云",
            "098地恶星没面目焦挺",
            "099地丑星石将军石勇",
            "100地数星小尉迟孙新",
            "101地阴星母大虫顾大嫂",
            "102地刑星菜园子张青",
            "103地壮星母夜叉孙二娘",
            "104地劣星活闪婆王定六",
            "105地健星险道神郁保四",
            "106地耗星白日鼠白胜",
            "107地贼星鼓上蚤时迁",
            "108地狗星金毛犬段景住"};

    RecyclerView mRecyclerView;
    MyAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        findViewById(R.id.addfooter).setOnClickListener(this);
        findViewById(R.id.addheader).setOnClickListener(this);

    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyAdapter(this);
        mAdapter.setData(Arrays.asList(DATA_ARRAY));
        mRecyclerView.setAdapter(mAdapter);


        mAdapter.setOnHeaderViewCreatedListener(new BaseRecyclerViewAdapter.OnHeaderViewCreatedListener() {
            @Override
            public void onHeaderViewCreated(View headerView) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addfooter:
                if (mAdapter.containsFooter()) {
                    mAdapter.removeFooter();
                    ((Button)view).setText("add footer");
                } else {
                    mAdapter.addFooter(R.layout.adapter_item_footer);
                    mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
                    ((Button)view).setText("remove footer");
                }
                break;
            case R.id.addheader:
                if (mAdapter.containsHeader()) {
                    mAdapter.removeHeader();
                    ((Button)view).setText("add header");
                } else {
                    mAdapter.addHeader(R.layout.adapter_item_header);
                    ((Button)view).setText("remove header");
                }
                mRecyclerView.scrollToPosition(0);
                break;
        }
    }
}
