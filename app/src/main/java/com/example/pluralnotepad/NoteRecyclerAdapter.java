package com.example.pluralnotepad;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder> {
        private final Context mcontext;
        private final List<NoteInfo> mNotes;
        private final LayoutInflater mlayoutInflater;
        private View itemView;

    public NoteRecyclerAdapter(Context context, List<NoteInfo> mNotes) {
        mcontext = context;
        this.mNotes = mNotes;
        mlayoutInflater = LayoutInflater.from(mcontext);
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View itemView=mlayoutInflater.inflate(R.layout.item_note_list,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NoteInfo note =mNotes.get(position);
        holder.mTextCourse.setText(note.getCourse().getTitle());
        holder.mTextTitle.setText(note.getTitle());
        holder.mCurrentPosition=position;
    }


    @Override
    public int getItemCount() {
        return  mNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView mTextCourse;
        private final TextView mTextTitle;
        private int   mCurrentPosition;

        public ViewHolder(View itemView){
            super(itemView);
            mTextCourse = (TextView) itemView.findViewById(R.id.text_course);
            mTextTitle = (TextView) itemView.findViewById(R.id.text_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mcontext,MainActivity.class);
                    intent.putExtra(MainActivity.NOTE_POSITION,mCurrentPosition);
                    mcontext.startActivity(intent);
                }
            });

        }
    }
}
