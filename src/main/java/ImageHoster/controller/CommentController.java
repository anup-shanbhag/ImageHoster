package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import ImageHoster.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class CommentController {
	@Autowired
	private ImageService imageService;

	@Autowired
	private CommentService commentService;

	// This controller method is called when the request pattern is of type
	// '/image/{imageId}/{imageTitle}/comments'
	// This method fetches the image with the corresponding id from the database and
	// prepares a comment and persists it to the database
	// Looks for a controller method with request mapping of type
	// '/images/{imageId}'
	@RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
	public String addComment(@PathVariable("imageId") Integer imageId, @RequestParam("comment") String commentText,
			Model model, HttpSession httpSession, @PathVariable("imageTitle") String title) {
		Image image = imageService.getImage(imageId);
		Comment comment = new Comment();
		comment.setDate(LocalDate.now());
		comment.setImage(image);
		comment.setText(commentText);
		comment.setUser((User) httpSession.getAttribute("loggeduser"));
		commentService.addComment(comment);
		return "redirect:/images/" + imageId + "/" + title;
	}
}
