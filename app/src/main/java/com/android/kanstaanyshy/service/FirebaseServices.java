package com.android.kanstaanyshy.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.kanstaanyshy.MainActivity;
import com.android.kanstaanyshy.R;
import com.android.kanstaanyshy.entity.AdminValuesModel;
import com.android.kanstaanyshy.entity.RatingModel;
import com.android.kanstaanyshy.service.moduls.MusicPlayer;
import com.android.kanstaanyshy.service.moduls.RemoveDuplicateList;
import com.android.kanstaanyshy.service.moduls.SearchFilter;
import com.android.kanstaanyshy.service.moduls.SpinnerSetValues;
import com.android.kanstaanyshy.view.Adapter.HistoryAdapter;
import com.android.kanstaanyshy.view.Adapter.LikeAdapter;
import com.android.kanstaanyshy.view.Adapter.LyricsAdapter;
import com.android.kanstaanyshy.view.Adapter.PlayListAdapter;
import com.android.kanstaanyshy.view.Adapter.PlayListFragmentAdapter;
import com.android.kanstaanyshy.view.Adapter.RatingAdapter;
import com.android.kanstaanyshy.view.Adapter.RecomendationAdapter;
import com.android.kanstaanyshy.view.Adapter.VictorinaAdapter;
import com.android.kanstaanyshy.view.PLayListFragment;
import com.android.kanstaanyshy.view.PlayingMusics;
import com.android.kanstaanyshy.view.Recomendation;
import com.android.kanstaanyshy.view.dialogPage.ConfirmDialogPage;
import com.android.kanstaanyshy.view.dialogPage.PlayListDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FirebaseServices {
    private DatabaseReference myRef;
    private PlayListFragmentAdapter playListAdapter;
    private RecomendationAdapter recomendationAdapter;
    private LikeAdapter likeAdapter;
    private Handler handler = new Handler();
    private PlayListAdapter play;

    private LyricsAdapter lyricsAdapter;
    private HistoryAdapter historyAdapter;

    public FirebaseServices(String path) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference(path);
    }

    public void readFromFirebasePlaylist(Context context, RecyclerView recyclerView, FragmentManager fragmentManagerReq, MediaPlayer mediaPlayer, String key, SearchView searchView, View view) {
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<AdminValuesModel> adminValuesModels = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map<String, Object> userData = (Map<String, Object>) snapshot.getValue();
                    AdminValuesModel adminValuesModel = new AdminValuesModel();
                    // Extract data from the userData Map
                    adminValuesModel.setKey((String) userData.get("key"));
                    adminValuesModel.setSong_name((String) userData.get("song_name"));
                    adminValuesModel.setJanr((String) userData.get("janr"));
                    adminValuesModel.setAutor((String) userData.get("autor"));
                    adminValuesModel.setLyrics((String) userData.get("lyrics"));
                    adminValuesModel.setHistory((String) userData.get("history"));
                    adminValuesModel.setImage((String) userData.get("image"));
                    adminValuesModel.setMusic((String) userData.get("music"));
                    adminValuesModel.setLiked((Boolean) userData.get("liked"));
                    adminValuesModel.setPlayListName((String) userData.get("playListName"));

                    if (adminValuesModel.getPlayListName().equals(key))
                        adminValuesModels.add(adminValuesModel);
                }

                playListAdapter = new PlayListFragmentAdapter(context, adminValuesModels, null, mediaPlayer, view);

                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(playListAdapter);

                playListAdapter.setOnItemClickListener(position -> {
                    FragmentManager fragmentManager = fragmentManagerReq;
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    PlayingMusics newFragment = new PlayingMusics();

                    Bundle bundle = new Bundle();
                    bundle.putString("key", adminValuesModels.get(position).getKey());
                    newFragment.setArguments(bundle);

                    fragmentTransaction.replace(R.id.fragment_container, newFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                });

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        SearchFilter.filterTextPlayList(newText, adminValuesModels, context, playListAdapter);
                        return true;
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(context, error.toException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void readFromFirebaseRecomendation(Context context, RecyclerView recyclerView, FragmentManager supportFragmentManager, MediaPlayer mediaPlayer, SearchView searchView, View view) {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<AdminValuesModel> adminValuesModels = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map<String, Object> userData = (Map<String, Object>) snapshot.getValue();
                    AdminValuesModel adminValuesModel = new AdminValuesModel();
                    // Extract data from the userData Map
                    adminValuesModel.setKey((String) userData.get("key"));
                    adminValuesModel.setSong_name((String) userData.get("song_name"));
                    adminValuesModel.setJanr((String) userData.get("janr"));
                    adminValuesModel.setAutor((String) userData.get("autor"));
                    adminValuesModel.setLyrics((String) userData.get("lyrics"));
                    adminValuesModel.setHistory((String) userData.get("history"));
                    adminValuesModel.setImage((String) userData.get("image"));
                    adminValuesModel.setMusic((String) userData.get("music"));
                    adminValuesModel.setLiked((Boolean) userData.get("liked"));
                    adminValuesModel.setPlayListName((String) userData.get("playListName"));

                    adminValuesModels.add(adminValuesModel);
                }

                recomendationAdapter = new RecomendationAdapter(context, adminValuesModels, null, mediaPlayer, view);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(recomendationAdapter);
                recomendationAdapter.setOnItemClickListener(position -> {
                    FragmentManager fragmentManager = supportFragmentManager;
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    PlayingMusics newFragment = new PlayingMusics();

                    Bundle bundle = new Bundle();
                    bundle.putString("key", adminValuesModels.get(position).getKey());
                    newFragment.setArguments(bundle);

                    fragmentTransaction.replace(R.id.fragment_container, newFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                });

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        SearchFilter.filterTextRecomendationAdapter(newText, adminValuesModels, context, recomendationAdapter);
                        return true;
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(context, error.toException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void readFromFirebaseLikes(Context context, RecyclerView recyclerView, FragmentManager supportFragmentManager, MediaPlayer mediaPlayer, SearchView searchView, View view) {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<AdminValuesModel> adminValuesModels = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map<String, Object> userData = (Map<String, Object>) snapshot.getValue();
                    AdminValuesModel adminValuesModel = new AdminValuesModel();
                    // Extract data from the userData Map
                    adminValuesModel.setKey((String) userData.get("key"));
                    adminValuesModel.setSong_name((String) userData.get("song_name"));
                    adminValuesModel.setJanr((String) userData.get("janr"));
                    adminValuesModel.setAutor((String) userData.get("autor"));
                    adminValuesModel.setLyrics((String) userData.get("lyrics"));
                    adminValuesModel.setHistory((String) userData.get("history"));
                    adminValuesModel.setImage((String) userData.get("image"));
                    adminValuesModel.setMusic((String) userData.get("music"));
                    adminValuesModel.setLiked((Boolean) userData.get("liked"));
                    adminValuesModel.setPlayListName((String) userData.get("playListName"));

                    if (adminValuesModel.getLiked())
                        adminValuesModels.add(adminValuesModel);
                }

                likeAdapter = new LikeAdapter(context, adminValuesModels, null, mediaPlayer, view);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(likeAdapter);

                likeAdapter.setOnItemClickListener(position -> {
                    FragmentManager fragmentManager = supportFragmentManager;
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    PlayingMusics newFragment = new PlayingMusics();

                    Bundle bundle = new Bundle();
                    bundle.putString("key", adminValuesModels.get(position).getKey());
                    newFragment.setArguments(bundle);

                    fragmentTransaction.replace(R.id.fragment_container, newFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                });

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        SearchFilter.filterTextLikeAdapter(newText, adminValuesModels, context, likeAdapter);
                        return true;
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(context, error.toException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void findFromFirebase(String keyToFind, TextView nameSong, TextView performer, TextView musicTime,
                                 ImageView songsImage, ImageView menuSongLyrics, ImageView likeSongLyrics,
                                 ImageView previousSongLyrics, ImageView playSongLyrics, ImageView nextSongLyrics,
                                 ImageView replySongLyrics, ImageView listSongLyrics, ImageView shareSongLyrics,
                                 MediaPlayer mediaPlayer, SeekBar seekBar, TextView lyricsMusic, Context context, FragmentTransaction fragmentTransaction, View view) {

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<AdminValuesModel> adminValuesModels = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map<String, Object> userData = (Map<String, Object>) snapshot.getValue();
                    AdminValuesModel adminValuesModel = new AdminValuesModel();
                    // Extract data from the userData Map
                    adminValuesModel.setKey((String) userData.get("key"));
                    adminValuesModel.setSong_name((String) userData.get("song_name"));
                    adminValuesModel.setJanr((String) userData.get("janr"));
                    adminValuesModel.setAutor((String) userData.get("autor"));
                    adminValuesModel.setLyrics((String) userData.get("lyrics"));
                    adminValuesModel.setHistory((String) userData.get("history"));
                    adminValuesModel.setImage((String) userData.get("image"));
                    adminValuesModel.setMusic((String) userData.get("music"));
                    adminValuesModel.setLiked((Boolean) userData.get("liked"));
                    adminValuesModel.setPlayListName((String) userData.get("playListName"));

                    adminValuesModels.add(adminValuesModel);

                }

                for (int i = 0; i < adminValuesModels.size(); i++) {
                    if (keyToFind.equals(adminValuesModels.get(i).getKey())) {
                        nameSong.setText(adminValuesModels.get(i).getSong_name());
                        performer.setText(adminValuesModels.get(i).getAutor());
                        lyricsMusic.setText(adminValuesModels.get(i).getLyrics());

                        int stepSong = i;

                        listSongLyrics.setOnClickListener(v -> {
                            mediaPlayer.pause();
                            fragmentTransaction.replace(R.id.fragment_container, new Recomendation()).commit();
                        });

                        playSongLyrics.setOnClickListener(v -> MusicPlayer.playerMusic(mediaPlayer, adminValuesModels, playSongLyrics, stepSong,"", nameSong, performer, lyricsMusic));

                        nextSongLyrics.setOnClickListener(v -> MusicPlayer.playerMusic(mediaPlayer, adminValuesModels, playSongLyrics, stepSong, "n",nameSong, performer, lyricsMusic));
                        previousSongLyrics.setOnClickListener(v -> MusicPlayer.playerMusic(mediaPlayer, adminValuesModels, playSongLyrics, stepSong, "p",nameSong, performer, lyricsMusic));

                        shareSongLyrics.setOnClickListener(v -> {
                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.setType("text/plain");
                            shareIntent.putExtra(Intent.EXTRA_SUBJECT, adminValuesModels.get(stepSong).getSong_name());
                            shareIntent.putExtra(Intent.EXTRA_TEXT, adminValuesModels.get(stepSong).getMusic());
                            context.startActivity(Intent.createChooser(shareIntent, "Share via"));
                        });

                        likeSongLyrics.setOnClickListener(v -> {
                            FirebaseServices firebaseServices = new FirebaseServices("Нац");
                            if (adminValuesModels.get(stepSong).getLiked()) {
                                likeSongLyrics.setColorFilter(R.color.green);
                                firebaseServices.updateLikes(adminValuesModels.get(stepSong).getKey(), false, context);
                            } else {
                                likeSongLyrics.setColorFilter(R.color.red);
                                firebaseServices.updateLikes(adminValuesModels.get(stepSong).getKey(), true, context);
                            }
                        });

                        menuSongLyrics.setOnClickListener(v -> {
                            PlayListDialog recomendationAdapter = new PlayListDialog(context, adminValuesModels, stepSong);
                            recomendationAdapter.show();
                        });
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


//        myRef.child(keyToFind).
//
//                addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.exists()) {
//                            AdminValuesModel adminValues = dataSnapshot.getValue(AdminValuesModel.class);
//
//                            nameSong.setText(adminValues.getSong_name());
//                            performer.setText(adminValues.getAutor());
//                            lyricsMusic.setText(adminValues.getLyrics());
//
//                            playSongLyrics.setOnClickListener(v -> {
//                                if (mediaPlayer.isPlaying()) {
//                                    mediaPlayer.pause();
//                                    playSongLyrics.setImageResource(R.drawable.baseline_play_arrow_24);
//                                } else {
//                                    try {
//                                        playSongLyrics.setImageResource(R.drawable.baseline_pause_circle_24);
//                                        mediaPlayer.reset();
//                                        mediaPlayer.setDataSource(adminValues.getMusic());
//                                        mediaPlayer.prepare();
//                                        mediaPlayer.start();
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            });
//
//                        } else {
//                            nameSong.setText("Ыр табылбады: " + keyToFind);
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        System.err.println("Error: " + databaseError.getMessage());
//                    }
//                });
    }

    public void updateLikes(String key, Boolean value, Context context) {
        myRef.child(key).child("liked").setValue(value)
                .addOnFailureListener(e -> Toast.makeText(context, "Ката чыкты, кайталап корунуз!", Toast.LENGTH_SHORT).show());
    }

    public void updatePlayList(String key, String value, Context context) {
        myRef.child(key).child("playListName").setValue(value)
                .addOnFailureListener(e -> Toast.makeText(context, "Ката чыкты, кайталап корунуз!", Toast.LENGTH_SHORT).show());

    }

    public void readFromFirebasePlaylistFragment(Context context, RecyclerView
            recyclerView, FragmentManager supportFragmentManager) {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<AdminValuesModel> adminValuesModels = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map<String, Object> userData = (Map<String, Object>) snapshot.getValue();
                    AdminValuesModel adminValuesModel = new AdminValuesModel();
                    // Extract data from the userData Map
                    adminValuesModel.setKey((String) userData.get("key"));
                    adminValuesModel.setSong_name((String) userData.get("song_name"));
                    adminValuesModel.setJanr((String) userData.get("janr"));
                    adminValuesModel.setAutor((String) userData.get("autor"));
                    adminValuesModel.setLyrics((String) userData.get("lyrics"));
                    adminValuesModel.setHistory((String) userData.get("history"));
                    adminValuesModel.setImage((String) userData.get("image"));
                    adminValuesModel.setMusic((String) userData.get("music"));
                    adminValuesModel.setLiked((Boolean) userData.get("liked"));
                    adminValuesModel.setPlayListName((String) userData.get("playListName"));

                    if (!adminValuesModel.getPlayListName().isEmpty())
                        adminValuesModels.add(adminValuesModel);
                }

                List<AdminValuesModel> adminValuesModelsMain = RemoveDuplicateList.removeDuplicates(adminValuesModels);

                play = new PlayListAdapter(context, adminValuesModelsMain, null);

                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(play);

                play.setOnItemClickListener(position -> {
                    FragmentManager fragmentManager = supportFragmentManager;
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    PLayListFragment newFragment = new PLayListFragment();

                    Bundle bundle = new Bundle();
                    bundle.putString("key", adminValuesModels.get(position).getPlayListName());
                    newFragment.setArguments(bundle);

                    fragmentTransaction.replace(R.id.fragment_container, newFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                });

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(context, error.toException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void readHistory(Context context, RecyclerView recyclerView, SearchView searchView) {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<AdminValuesModel> adminValuesModels = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map<String, Object> userData = (Map<String, Object>) snapshot.getValue();
                    AdminValuesModel adminValuesModel = new AdminValuesModel();
                    // Extract data from the userData Map
                    adminValuesModel.setKey((String) userData.get("key"));
                    adminValuesModel.setSong_name((String) userData.get("song_name"));
                    adminValuesModel.setJanr((String) userData.get("janr"));
                    adminValuesModel.setAutor((String) userData.get("autor"));
                    adminValuesModel.setLyrics((String) userData.get("lyrics"));
                    adminValuesModel.setHistory((String) userData.get("history"));
                    adminValuesModel.setImage((String) userData.get("image"));
                    adminValuesModel.setMusic((String) userData.get("music"));
                    adminValuesModel.setLiked((Boolean) userData.get("liked"));
                    adminValuesModel.setPlayListName((String) userData.get("playListName"));

                    adminValuesModels.add(adminValuesModel);
                }

                historyAdapter = new HistoryAdapter(context, adminValuesModels, null);

                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(historyAdapter);

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        SearchFilter.filterTextHistoryAdapter(newText, adminValuesModels, context, historyAdapter);
                        return true;
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(context, error.toException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void victorinaFirebase(Context context, RecyclerView recyclerView) {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<AdminValuesModel> adminValuesModels = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map<String, Object> userData = (Map<String, Object>) snapshot.getValue();
                    AdminValuesModel adminValuesModel = new AdminValuesModel();
                    // Extract data from the userData Map
                    adminValuesModel.setKey((String) userData.get("key"));
                    adminValuesModel.setSong_name((String) userData.get("song_name"));
                    adminValuesModel.setJanr((String) userData.get("janr"));
                    adminValuesModel.setAutor((String) userData.get("autor"));
                    adminValuesModel.setLyrics((String) userData.get("lyrics"));
                    adminValuesModel.setHistory((String) userData.get("history"));
                    adminValuesModel.setImage((String) userData.get("image"));
                    adminValuesModel.setMusic((String) userData.get("music"));
                    adminValuesModel.setLiked((Boolean) userData.get("liked"));
                    adminValuesModel.setPlayListName((String) userData.get("playListName"));

                    adminValuesModels.add(adminValuesModel);
                }

                VictorinaAdapter victorinaAdapter = new VictorinaAdapter(context, adminValuesModels, null);

                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(victorinaAdapter);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(context, error.toException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void saveAuth(Context context, String name_txt, String email_txt, String
            phone_txt, String password_txt) {

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean checker = false;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map<String, Object> userData = (Map<String, Object>) snapshot.getValue();
                    // Extract data from the userData Map
                    String loginf = (String) userData.get("email");
                    if (email_txt.equals(loginf)) {
                        checker = true;
                    }

                }
                if (!checker) {
                    RatingModel adminValuesModel = new RatingModel(name_txt, email_txt, phone_txt, password_txt, "0");
                    adminValuesModel.setKey(myRef.push().getKey());
                    myRef.child(adminValuesModel.getKey()).setValue(adminValuesModel);
                    new ConfirmDialogPage(context).showConfirmDialogPage("Ийгиликтуу сакталды!", " Логин болумунон кириниз",
                            "");
                } else {
                    Toast.makeText(context, "Окшош e-mail бар, жаны e-mail жазыныз", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(context, error.toException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void readFromFirebaseAuth(Activity activity, Context context, String email, String
            password) {

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean checker = false;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map<String, Object> userData = (Map<String, Object>) snapshot.getValue();
                    // Extract data from the userData Map
                    String loginf = (String) userData.get("email");
                    String passwordf = (String) userData.get("password");
                    if (email.equals(loginf) && password.equals(passwordf)) {
                        checker = true;
                        activity.startActivity(new Intent(context, MainActivity.class));
                        break;
                    }

                }
                if (!checker)
                    Toast.makeText(context, "Сиздин атыныздан колдонуучу жок экен (", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(context, error.toException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void ratingFirebase(Context context, RecyclerView recyclerViewRating) {

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<RatingModel> ratingModels = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map<String, Object> userData = (Map<String, Object>) snapshot.getValue();
                    RatingModel ratingModel = new RatingModel();
                    // Extract data from the userData Map
                    ratingModel.setEmail((String) userData.get("email"));
                    ratingModel.setKey((String) userData.get("key"));
                    ratingModel.setName((String) userData.get("name"));
                    ratingModel.setPassword((String) userData.get("password"));
                    ratingModel.setPhoneNumber((String) userData.get("phoneNumber"));
                    ratingModel.setPuan((String) userData.get("puan"));

                    ratingModels.add(ratingModel);

                }

                RatingAdapter ratingAdapter = new RatingAdapter(context, ratingModels, null);

                recyclerViewRating.setLayoutManager(new LinearLayoutManager(context));
                recyclerViewRating.setAdapter(ratingAdapter);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(context, error.toException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void readFirebaseSpinner(Spinner spinner, Context context, EditText editText) {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<String> adminValuesModels = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map<String, Object> userData = (Map<String, Object>) snapshot.getValue();
                    AdminValuesModel adminValuesModel = new AdminValuesModel();
                    // Extract data from the userData Map
                    adminValuesModel.setKey((String) userData.get("key"));
                    adminValuesModel.setSong_name((String) userData.get("song_name"));
                    adminValuesModel.setJanr((String) userData.get("janr"));
                    adminValuesModel.setAutor((String) userData.get("autor"));
                    adminValuesModel.setLyrics((String) userData.get("lyrics"));
                    adminValuesModel.setHistory((String) userData.get("history"));
                    adminValuesModel.setImage((String) userData.get("image"));
                    adminValuesModel.setMusic((String) userData.get("music"));
                    adminValuesModel.setLiked((Boolean) userData.get("liked"));
                    adminValuesModel.setPlayListName((String) userData.get("playListName"));

                    if (!adminValuesModel.getPlayListName().isEmpty()) {
                        adminValuesModels.add(adminValuesModel.getPlayListName());
                    }
                }
                new SpinnerSetValues(adminValuesModels, context, editText).show(spinner);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(context, error.toException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
