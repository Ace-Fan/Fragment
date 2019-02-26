package com.example.fragment;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import Adapter.BannerAdapter;

/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment1 extends Fragment {
    ImageView imge;
    ImageView imge2;
    ImageView imge3;

    public static final int CAROUSEL_TIME = 5000;//banner 滚动间隔
    private ViewPager vpBanner;//
    private ViewGroup viewGroup;//显示点点点图片，可以看到ViewPager当前选中状态

    private BannerAdapter bannerAdapter;//ViewPager适配器

    private Handler handler = new Handler();
    private int currentItem = 0;//ViewPager当前位置

    private final Runnable mTicker = new Runnable() {
        public void run() {
            long now = SystemClock.uptimeMillis();
            long next = now + (CAROUSEL_TIME - now % CAROUSEL_TIME);

            handler.postAtTime(mTicker, next);//延迟5秒再次执行runnable,就跟计时器一样效果

            currentItem++;
            vpBanner.setCurrentItem(currentItem);
        }
    };
    /*public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        imge=(ImageView)view.findViewById(R.id.img3);
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }*/

    public MyFragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content1, container, false);
        //TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        //txt_content.setText("第一个Fragment");
        imge = (ImageView) view.findViewById(R.id.img3);
        imge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(getActivity(), Denglu.class);
                startActivity(intents);
            }
        });

        imge2 = (ImageView) view.findViewById(R.id.img2);
        imge2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(),XinWenMainActivity.class);
                startActivity(it);
            }
        });

        imge3 = (ImageView) view.findViewById(R.id.img5);
        imge3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(),JiaZhengMainActivity.class);
                startActivity(it);
            }
        });


        //********************
        vpBanner = view.findViewById(R.id.vp_banner);
        bannerAdapter = new BannerAdapter(getActivity());//初始化适配器
        bannerAdapter.setOnBannerClickListener(onBannerClickListener);//图片点击监听
        vpBanner.setOffscreenPageLimit(2);//缓存页数
        vpBanner.setAdapter(bannerAdapter);//设置适配器
        vpBanner.addOnPageChangeListener(onPageChangeListener);//页面改变监听

        viewGroup = view.findViewById(R.id.viewGroup);//显示点点点控件

        //将点点加入到ViewGroup中
        for (int i = 0; i < bannerAdapter.getBanners().length; i++) {
            ImageView imageView = new ImageView(getActivity());
            //设置图片宽高
            imageView.setLayoutParams(new ViewGroup.LayoutParams(10, 10));
            if (i == 0) {
                imageView.setBackgroundResource(R.drawable.ic_dot_normal);//第一个默认选中
            } else {
                imageView.setBackgroundResource(R.drawable.ic_dot_focused);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 10;//左边距
            layoutParams.rightMargin = 10;//右边距
            viewGroup.addView(imageView, layoutParams);
        }

        //给ViewPager设置当前页，这样刚打开软件也能向左滑动
        currentItem = bannerAdapter.getBanners().length * 50;
        vpBanner.setCurrentItem(currentItem);

        handler.postDelayed(mTicker, CAROUSEL_TIME);//开启计时器
        //
        Log.e("HEHE", "1日狗");
        return view;
    }

    private ViewPager.OnPageChangeListener onPageChangeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageSelected(int position) {
            currentItem = position;

            //改变点点点图片的选中状态
            setImageBackground(position % bannerAdapter.getBanners().length);
        }

        @Override
        public void onPageScrollStateChanged(int state) {}
    };

    //******

        private void setImageBackground(int selectItems) {
            for (int i = 0; i < bannerAdapter.getBanners().length; i++) {
                ImageView imageView = (ImageView) viewGroup.getChildAt(i);
                imageView.setBackgroundDrawable(null);//先把背景设置成无
                if (i == selectItems) {
                    imageView.setImageResource(R.drawable.ic_dot_normal);
                } else {
                    imageView.setImageResource(R.drawable.ic_dot_focused);
                }
            }
        }

        private View.OnClickListener onBannerClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=(Integer) view.getTag();//从tag中取出当前点击的ImageView的位置
                Toast.makeText(getActivity(),"重要消息:"+position,Toast.LENGTH_LONG).show();
            }
        };

        @Override
        public void onDestroy() {
            super.onDestroy();
            handler.removeCallbacks(mTicker);//删除计时器
        }
    }
