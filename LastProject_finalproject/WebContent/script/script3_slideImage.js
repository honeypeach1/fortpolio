	//Slideshows
   var sslideIndex = 1;

   function splusDivs(n) {
    sslideIndex = sslideIndex + n;
    sshowDivs(sslideIndex);
   }

   function sshowDivs(n) {
    var x = document.getElementsByClassName("smy_Slides");
    if (n > x.length) {
     sslideIndex = 1
    }
    if (n < 1) {
     sslideIndex = x.length
    }
    ;
    for (i = 0; i < x.length; i++) {
     x[i].style.display = "none";
    }
    x[sslideIndex - 1].style.display = "block";
   }

   sshowDivs(1);