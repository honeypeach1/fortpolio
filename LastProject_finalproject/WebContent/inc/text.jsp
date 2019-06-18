<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>The Third Milestone(가제)</title>
</head>
<body>

<div class="super_container">
	
	<jsp:include page="header.jsp" flush="false"/>

	<!-- Menu -->

	<div class="menu">
		<div class="background_image" style="background-image:url(images/menu.jpg)"></div>
		<div class="menu_content d-flex flex-column align-items-center justify-content-center">
			<ul class="menu_nav_list text-center">
				<li><a href="#">Home</a></li>
				<li><a href="#">About us</a></li>
				<li><a href="#">Rooms</a></li>
				<li><a href="#">News</a></li>
				<li><a href="#">Contact</a></li>
			</ul>
			<div class="menu_review"><a href="#">Book Now</a></div>
		</div>
	</div>

	<!-- Home -->

	<div class="home">
		<div class="parallax_background parallax-window" data-parallax="scroll" data-image-src="images/mainImage.jpg" data-speed="0.8"></div>
		<div class="home_container">
			<div class="container">
				<div class="row">
					<div class="col">
						<div class="home_content text-center">
							<div class="home_title"><h1><font style="font-size: 85px">Hotel & Resort</font></h1></div>
							<div class="home_text"><font style="font-size: 25px">What do you want Accommodations? Anything searching know!</font></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Search Box -->

	<div class="search_box" style="height: 120px">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="search_box_container d-flex flex-row align-items-center justify-content-start">
						<div class="search_form_container">
							<form action="#" id="search_form" class="search_form">
								<div class="d-flex flex-lg-row flex-column align-items-center justify-content-start">
									<ul class="search_form_list d-flex flex-row align-items-center justify-content-start flex-wrap">
										<li class="search_dropdown d-flex flex-row align-items-center justify-content-start">
											<span>Check in</span>
											<i class="fa fa-chevron-down ml-auto" aria-hidden="true"></i>
											<ul>
												<li>Check in item 1</li>
												<li>Check in item 2</li>
												<li>Check in item 3</li>
												<li>Check in item 4</li>
												<li>Check in item 5</li>
											</ul>
										</li>
										<li class="search_dropdown d-flex flex-row align-items-center justify-content-start">
											<span>Check out</span>
											<i class="fa fa-chevron-down ml-auto" aria-hidden="true"></i>
											<ul>
												<li>Check out item 1</li>
												<li>Check out item 2</li>
												<li>Check out item 3</li>
												<li>Check out item 4</li>
												<li>Check out item 5</li>
											</ul>
										</li>
										<li class="search_dropdown d-flex flex-row align-items-center justify-content-start">
											<span>Guests</span>
											<i class="fa fa-chevron-down ml-auto" aria-hidden="true"></i>
											<ul>
												<li>1</li>
												<li>2</li>
												<li>3</li>
												<li>4</li>
												<li>5</li>
											</ul>
										</li>
										<li class="search_dropdown d-flex flex-row align-items-center justify-content-start">
											<span>Children</span>
											<i class="fa fa-chevron-down ml-auto" aria-hidden="true"></i>
											<ul>
												<li>1</li>
												<li>2</li>
												<li>3</li>
												<li>4</li>
												<li>5</li>
											</ul>
										</li>
										<li class="search_dropdown d-flex flex-row align-items-center justify-content-start">
											<span>Rooms</span>
											<i class="fa fa-chevron-down ml-auto" aria-hidden="true"></i>
											<ul>
												<li>1</li>
												<li>2</li>
												<li>3</li>
												<li>4</li>
												<li>5</li>
											</ul>
										</li>
									</ul>
									<button class="search_button">search</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Intro -->

	<div class="intro">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="section_title_container text-center">
						<div class="section_title"><h1>Beach Hotel- More than a stay</h1></div>
						<div class="section_text">In vitae nisi aliquam, scelerisque leo a, volutpat sem. Vivamus rutrum dui fermentum eros hendrerit, id lobortis leo volutpat. Maecenas sollicitudin est in libero pretium interdum.</div>
					</div>
				</div>
			</div>
			<div class="row intro_row">
				
				<!-- Intro Image -->
				<div class="col-lg-6">
					<div class="intro_image"><img src="images/intro.jpg" alt="https://unsplash.com/@papao03"></div>
				</div>

				<!-- Intro Content -->
				<div class="col-lg-6 intro_col">
					<div class="intro_content">
						<div class="quote"><img src="images/quote.png" alt=""></div>
						<div class="intro_text">
							<p>In vitae nisi aliquam, scelerisque leo a, volutpat sem. Vivamus rutrum dui fermentum eros hendrerit, id lobortis leo volutpat. Maecenas sollicitudin est in libero pretium interdum. Nullam volutpat dui sem, ac congue purus hendrerit, id lobortis leo luctus nec.</p>
						</div>
						<div class="intro_author d-flex flex-row align-items-center justify-content-start">
							<div class="author_image"><img src="images/author_1.jpg" alt="https://unsplash.com/@onurozkardes"></div>
							<div class="intro_author_content">
								<div class="intro_author_name">Michael Williams</div>
								<div class="intro_author_title">client</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Room -->

	<div class="room d-flex flex-lg-row flex-column align-items-start justify-content-start">
		<div class="room_content">
			<div class="container">
				<div class="row">
					<div class="col">
						<div class="room_title">
							<div class="section_title_container_2">
								<div class="section_title"><h1>Deluxe Room</h1></div>
							</div>
							<div class="room_price">From <span>$190</span>/ night</div>
						</div>
						<div class="room_list">
							<ul>
								<li class="d-flex flex-row align-items-start justify-content-start">
									<div><div>Bed:</div></div>
									<div>Double bed</div>
								</li>
								<li class="d-flex flex-row align-items-start justify-content-start">
									<div><div>Capacity:</div></div>
									<div>2 adults</div>
								</li>
								<li class="d-flex flex-row align-items-start justify-content-start">
									<div><div>Room size:</div></div>
									<div>55mÂ²</div>
								</li>
								<li class="d-flex flex-row align-items-start justify-content-start">
									<div><div>View:</div></div>
									<div>Sea view</div>
								</li>
								<li class="d-flex flex-row align-items-start justify-content-start">
									<div><div>Facilities:</div></div>
									<div>Iron, TV, Hair dryer</div>
								</li>
							</ul>
						</div>
						<div class="button room_button trans_200"><a href="#">book now</a></div>
					</div>
				</div>
			</div>		
		</div>
		<div class="room_image">
			<div class="background_image" style="background-image:url(images/room_1.jpg)"></div>
		</div>
	</div>

	<!-- Facilities -->

	<div class="facilities">
		<div class="container">
			<div class="row icon_box_row">

				<!-- Icon Box -->
				<div class="col-lg-4">
					<div class="icon_box text-center">
						<div class="icon_box_icon"><img src="images/icon_1.svg" alt="https://www.flaticon.com/authors/monkik"></div>
						<div class="icon_box_title"><h2>Beautiful Rooms</h2></div>
						<div class="icon_box_text">
							<p>In vitae nisi aliquam, scelerisque leo a, volutpat sem. Vivamus rutrum dui fermentum eros hendrerit, id lobortis leo volutpat.</p>
						</div>
					</div>
				</div>

				<!-- Icon Box -->
				<div class="col-lg-4">
					<div class="icon_box text-center">
						<div class="icon_box_icon"><img src="images/icon_2.svg" alt="https://www.flaticon.com/authors/monkik"></div>
						<div class="icon_box_title"><h2>Swimming Pool</h2></div>
						<div class="icon_box_text">
							<p>In vitae nisi aliquam, scelerisque leo a, volutpat sem. Vivamus rutrum dui fermentum eros hendrerit, id lobortis leo volutpat.</p>
						</div>
					</div>
				</div>

				<!-- Icon Box -->
				<div class="col-lg-4">
					<div class="icon_box text-center">
						<div class="icon_box_icon"><img src="images/icon_3.svg" alt="https://www.flaticon.com/authors/monkik"></div>
						<div class="icon_box_title"><h2>Luxury Resort</h2></div>
						<div class="icon_box_text">
							<p>In vitae nisi aliquam, scelerisque leo a, volutpat sem. Vivamus rutrum dui fermentum eros hendrerit, id lobortis leo volutpat.</p>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<!-- Gallery -->

	<div class="gallery">
		<div class="gallery_slider_container">

			<!-- Gallery Slider -->
			<div class="owl-carousel owl-theme gallery_slider">
				
				<!-- Slide -->
				<div class="owl-item gallery_item">
					<a class="colorbox" href="images/gallery_1.jpg">
						<div class="background_image" style="background-image:url(images/gallery_1.jpg)"></div>
					</a>
					<div class="gallery_overlay trans_200 d-flex flex-column align-items-center justify-content-center"><div>+</div></div>
				</div>

				<!-- Slide -->
				<div class="owl-item gallery_item">
					<a class="colorbox" href="images/gallery_2.jpg">
						<div class="background_image" style="background-image:url(images/gallery_2.jpg)"></div>
					</a>
					<div class="gallery_overlay trans_200 d-flex flex-column align-items-center justify-content-center"><div>+</div></div>
				</div>

				<!-- Slide -->
				<div class="owl-item gallery_item">
					<a class="colorbox" href="images/gallery_3.jpg">
						<div class="background_image" style="background-image:url(images/gallery_3.jpg)"></div>
					</a>
					<div class="gallery_overlay trans_200 d-flex flex-column align-items-center justify-content-center"><div>+</div></div>
				</div>

				<!-- Slide -->
				<div class="owl-item gallery_item">
					<a class="colorbox" href="images/gallery_4.jpg">
						<div class="background_image" style="background-image:url(images/gallery_4.jpg)"></div>
					</a>
					<div class="gallery_overlay trans_200 d-flex flex-column align-items-center justify-content-center"><div>+</div></div>
				</div>

				<!-- Slide -->
				<div class="owl-item gallery_item">
					<a class="colorbox" href="images/gallery_5.jpg">
						<div class="background_image" style="background-image:url(images/gallery_5.jpg)"></div>
					</a>
					<div class="gallery_overlay trans_200 d-flex flex-column align-items-center justify-content-center"><div>+</div></div>
				</div>

			</div>
		</div>
	</div>

	<!-- Newsletter -->

	<div class="newsletter">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="section_title_container_2">
						<div class="section_title"><h1>Our Newsletter</h1></div>
					</div>
				</div>
			</div>
			<div class="row newsletter_row">
				
				<!-- Newsletter Content -->
				<div class="col-lg-6">
					<div class="newsletter_content">
						<div class="newsletter_text">
							<p>In vitae nisi aliquam, scelerisque leo a, volutpat sem. Vivamus rutrum dui fermentum eros hendrerit, id lobortis leo volutpat. Maecenas sollicitudin est in libero pretium interdum.</p>
						</div>
					</div>
				</div>

				<!-- Newsletter Form -->
				<div class="col-lg-6 newsletter_col">
					<form action="#" class="newsletter_form" id="newsletter_form">
						<input type="email" class="newsletter_input" placeholder="Your email" required="required">
						<button class="newsletter_button">subscribe</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Contact -->

	<div class="contact">
		<div class="container">
			<div class="row">
				
				<!-- Contact Map -->
				<div class="col-xl-6">
					<div class="contact_map_container">
						
						<!-- Contact Map -->
						<div class="contact_map">

							<!-- Google Map -->
							<div class="map">
								<div id="google_map" class="google_map">
									<div class="map_container">
										<div id="map"></div>
									</div>
								</div>
							</div>

						</div>

						<!-- Contact Info Box -->
						<div class="contact_info_box d-flex flex-column align-items-center justify-content-center">
							<ul class="contact_info_list">
								<li class="d-flex flex-row align-items-start justify-content-start">
									<div><div class="contact_info_icon d-flex flex-column align-items-center justify-content-center"><img src="images/placeholder.png" alt=""></div></div>
									<div class="contact_info_text">1481 Creekside Lane Avila Beach, CA 931</div>
								</li>
								<li class="d-flex flex-row align-items-center justify-content-start">
									<div><div class="contact_info_icon d-flex flex-column align-items-center justify-content-center"><img src="images/smartphone.png" alt=""></div></div>
									<div class="contact_info_text">+53 345 7953 32453</div>
								</li>
								<li class="d-flex flex-row align-items-center justify-content-start">
									<div><div class="contact_info_icon d-flex flex-column align-items-center justify-content-center"><img src="images/mail.png" alt=""></div></div>
									<div class="contact_info_text">yourmail@gmail.com</div>
								</li>
							</ul>
						</div>

					</div>
				</div>
				
				<!-- Contact Form -->
				<div class="col-xl-6 contact_col">
					<div class="contact_form_container">
						<div class="section_title_container_2">
							<div class="section_title"><h1>Contact Info</h1></div>
							<div class="section_text">Vivamus rutrum dui fermentum eros hendrerit, id lobortis leo volutpat. Maecenas sollicitudin est in libero pretium interdum.</div>
						</div>
						<form action="#" class="contact_form" id="contact_form">
							<div class="row contact_row">
								<div class="col-md-6"><input type="text" class="contact_input" placeholder="Name" required="required"></div>
								<div class="col-md-6"><input type="email" class="contact_input" placeholder="E-mail" required="required"></div>
							</div>
							<div><textarea class="contact_input contact_textarea" placeholder="Message" required="required"></textarea></div>
							<button class="contact_button">send message</button>
						</form>
					</div>
				</div>
			</div>
		</div>

	</div>
	<jsp:include page="Footer.jsp" flush="false"/>
</div>

<script src="incCSSjs/jquery-3.2.1.min.js"></script>
<script src="incCSSstyles/bootstrap-4.1.2/popper.js"></script>
<script src="incCSSstyles/bootstrap-4.1.2/bootstrap.min.js"></script>
<script src="incCSSplugins/greensock/TweenMax.min.js"></script>
<script src="incCSSplugins/greensock/TimelineMax.min.js"></script>
<script src="incCSSplugins/scrollmagic/ScrollMagic.min.js"></script>
<script src="incCSSplugins/greensock/animation.gsap.min.js"></script>
<script src="plugins/greensock/ScrollToPlugin.min.js"></script>
<script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="plugins/easing/easing.js"></script>
<script src="plugins/progressbar/progressbar.min.js"></script>
<script src="plugins/colorbox/jquery.colorbox-min.js"></script>
<script src="plugins/parallax-js-master/parallax.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyCIwF204lFZg1y4kPSIhKaHEXMLYxxuMhA"></script>
<script src="js/custom.js"></script>
</body>
</html>
</body>
</html>