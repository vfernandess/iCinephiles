package com.arctouch.codechallenge.view.feature.home.view;

public class HomeAdapter { // extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

//    private List<Movie> movies;
//
//    public HomeAdapter(List<Movie> movies) {
//        this.movies = movies;
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//
//        private final MovieImageUrlBuilder movieImageUrlBuilder = new MovieImageUrlBuilder();
//
//        private final TextView titleTextView;
//        private final TextView genresTextView;
//        private final TextView releaseDateTextView;
//        private final ImageView posterImageView;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            titleTextView = itemView.findViewById(R.id.titleTextView);
//            genresTextView = itemView.findViewById(R.id.genresTextView);
//            releaseDateTextView = itemView.findViewById(R.id.releaseDateTextView);
//            posterImageView = itemView.findViewById(R.id.posterImageView);
//        }
//
//        public void bind(Movie movie) {
//            titleTextView.setText(movie.title);
//            genresTextView.setText(TextUtils.join(", ", movie.genres));
//            releaseDateTextView.setText(movie.releaseDate);
//
//            String posterPath = movie.posterPath;
//            if (TextUtils.isEmpty(posterPath) == false) {
//                Glide.with(itemView)
//                        .load(movieImageUrlBuilder.buildPosterUrl(posterPath))
//                        .apply(new RequestOptions().placeholder(R.drawable.ic_image_placeholder))
//                        .into(posterImageView);
//            }
//        }
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public int getItemCount() {
//        return movies.size();
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.bind(movies.get(position));
//    }
}
