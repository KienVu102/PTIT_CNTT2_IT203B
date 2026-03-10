import java.util.List;

class Post {
    private List<String> tags;

    public Post(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
    }
}

public class Ex_6 {
    public static void main(String[] args) {

        List<Post> posts = List.of(
                new Post(List.of("java", "backend")),
                new Post(List.of("python", "data")));

        List<String> allTags = posts.stream()
                .flatMap(post -> post.getTags().stream())
                .toList();

        System.out.println(allTags);
    }
}