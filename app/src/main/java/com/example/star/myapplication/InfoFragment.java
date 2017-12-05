package com.example.star.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class InfoFragment extends Fragment {

    private String title;
    private String kind;
    private String address;
    private String opentime;
    private String closetime;
    private String phonenumber;
    private View container;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.infoview,null);

        Intent intent=getActivity().getIntent();
        title =intent.getStringExtra("title");
        kind =intent.getStringExtra("kind");
        address =intent.getStringExtra("address");
        opentime =intent.getStringExtra("opentime");
        closetime =intent.getStringExtra("closetime");
        phonenumber =intent.getStringExtra("phonenumber");

        TextView infotitle = (TextView)view.findViewById(R.id.infotitle);
        TextView infokind = (TextView)view.findViewById(R.id.infokind);
        TextView infoaddress = (TextView)view.findViewById(R.id.infoaddress);
        TextView infotime = (TextView)view.findViewById(R.id.infotime);
        Button button1 = (Button)view.findViewById(R.id.callbtn);
        Button button2 = (Button)view.findViewById(R.id.sharebtn);
        Button button3 = (Button)view.findViewById(R.id.reservbtn);
        Button.OnClickListener bClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){ // 현재 버전 == 마시멜로우(M) 버전보다 높은지 확인
                    /**
                     * 사용자 단말기의 권한 중 "전화걸기" 권한이 허용되어 있는지 체크한다.
                     */
                    int permissionResult = getActivity().checkSelfPermission(Manifest.permission.CALL_PHONE);
                    // Manifest 와 Permission 은 Android 로 Import 한다.

                    // CALL_PHONE 의 권한이 없을때
                    if ( permissionResult == PackageManager.PERMISSION_DENIED ){ // 현재 App 에서 권한에 대해서 DENIED 되어 있는지 알아보기
                        /**
                         * 사용자가 CALL_PHONE 권한을 한번이라도 "거부" 한 적이 있는지 조사한다.
                         * 거부한 적이 한번이라도 있다면, true 를 리턴한다.
                         * 거부한 이력이 없다면, False 를 리턴한다.
                         */
                        if( shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE) ){
                            AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                            dialog.setTitle("권한이 필요합니다.")
                                    .setMessage("이 기능을 사용하기 위해서는 단말기의 \"전화걸기\" 권한이 필요합니다. 계속하시겠습니까?")
                                    .setPositiveButton("네", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
                                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);
                                            }
                                        }
                                    })
                                    .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(getActivity(),"기능을 취소했습니다.", Toast.LENGTH_SHORT).show();

                                        }
                                    }).create().show();
                        }
                        // 최초로 권한을 요청 할 때
                        else{
                            // CALL_PHONE 권한을 Android OS 에 요청한다.
                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);
                        }
                    }
                    // CALL_PHONE 의 권한이 있을때
                    else{
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phonenumber));
                        startActivity(intent);
                    }
                }
                else { // 사용자의 OS Version이 마시멜로우 이하일때
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phonenumber));
                    startActivity(intent);
                }


            }
        };
        Button.OnClickListener aClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                container.buildDrawingCache();
                Bitmap captureView = container.getDrawingCache();

                String ad = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/com.jinsoo/Jinsoo" + "capture.jpeg";
                FileOutputStream fos;
                try{
                    fos = new FileOutputStream(ad);
                    captureView.compress(Bitmap.CompressFormat.JPEG,100,fos);
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }

                //Intent intent = new Intent();
                Uri uri = Uri.fromFile(new File(ad));
                Intent intent= new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_STREAM,uri);
                intent.setType("image/*");
                //intent.putExtra(Intent.EXTRA_SUBJECT, title);
                //intent.putExtra(Intent.EXTRA_TEXT, kind);

                PackageManager packManager = getActivity().getPackageManager(); // mcontext
                List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

                boolean resolved = false;
                for(ResolveInfo resolveInfo: resolvedInfoList) {
                    if(resolveInfo.activityInfo.packageName.startsWith("com.facebook.katana")){
                        intent.setClassName(
                                resolveInfo.activityInfo.packageName,
                                resolveInfo.activityInfo.name );
                        resolved = true;
                        break;
                    }
                }

                if(resolved) {
                    startActivity(Intent.createChooser(intent,"공유"));

                } else {
                    Toast.makeText(getActivity(), "페이스북 앱이 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        };

        Button.OnClickListener cClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new AlarmHATT(getActivity()).Alarm();
                Toast.makeText(getActivity(), "예약이 완료됐습니다.", Toast.LENGTH_SHORT).show();
            }
        };

        button2.setOnClickListener(aClickListener);
        button1.setOnClickListener(bClickListener);
        button3.setOnClickListener(cClickListener);

        infotitle.setText(title);
        infokind.setText(kind);
        infoaddress.setText(address);
        infotime.setText(opentime +" ~ "+closetime);

        return view;
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if ( requestCode == 1000 ) {
            // 요청한 권한을 사용자가 "허용" 했다면...
            if( grantResults.length > 0 && grantResults[0]  == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:010-0000-0000"));
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent);
                }
            }
            else{
                Toast.makeText(getActivity(), "권한요청을 거부했습니다." , Toast.LENGTH_SHORT).show();
            }
        }
    }
    public class AlarmHATT { // Manifest 부분에 진동, 홀드상태 활성화 두개의 퍼미션 추가
        Context context;
        public AlarmHATT(Context context) {
            this.context=context;
        }
        public void Alarm() {
            AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(getActivity(), Receiver.class);
            intent.putExtra("title",title);
            intent.putExtra("address",address);
            intent.putExtra("opentime",opentime);
            PendingIntent sender = PendingIntent.getBroadcast(getActivity(), 0, intent, 0 );
            Calendar cal;
            cal= Calendar.getInstance(Locale.KOREA);
            //알람 예약
            am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), sender);
        }
    }

}