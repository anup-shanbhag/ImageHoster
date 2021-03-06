package ImageHoster.model;

import javax.persistence.*;
import java.time.LocalDate;

//@Entity annotation specifies that the corresponding class is a JPA entity
@Entity
//@Table annotation provides more options to customize the mapping.
//Here the name of the table to be created in the database is explicitly mentioned as 'comments'.
// Hence the table named 'comments' will be created in the database with all the columns mapped to all the attributes in 'Comment' class
@Table(name = "comments")
public class Comment {
	// @Id annotation specifies that the corresponding attribute is a primary key
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// @Column annotation specifies that the attribute will be mapped to the column
	// in the database.
	// Here the column name is explicitly mentioned as 'id'
	@Column(name = "id")
	private Integer id;
	// Text is a Postgres specific column type that allows you to save
	// text based data that will be longer than 256 characters
	// this is a content of a user's comment
	@Column(name = "text", columnDefinition = "TEXT")
	private String text;

	@Column(name = "date")
	LocalDate date;

	// The 'comments' table is mapped to 'users' table with Many:One mapping
	// One comment can have only one user (owner) but one user can have multiple
	// comments
	// FetchType is Lazy
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	User user;

	// The 'comments' table is mapped to 'images' table with Many:One mapping
	// One comment can be mapped to only one image but one image can have multiple
	// comments
	// FetchType is Lazy
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "image_id")
	Image image;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
