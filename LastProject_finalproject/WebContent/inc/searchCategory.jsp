<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
// ----------- 여기서부터 카테고리 css --------------

form {
  background: #fff;
  //border-radius: 20px;
  //box-shadow: 5px 5px 15px rgba(107, 173, 182, 0.6);
  padding: 1rem 2rem;
  min-width: 30vw;
}
form li {
  margin: 0.3rem 0;
}
form div {
  margin: 1.5rem 0;
}

ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

input[type="checkbox"],
input[type="radio"] {
  border: 0;
  clip: rect(0 0 0 0);
  height: 1px;
  margin: -1px;
  overflow: hidden;
  padding: 0;
  position: absolute;
  width: 1px;
}
input[type="checkbox"]:hover + label:before,
input[type="radio"]:hover + label:before {
  border-color: #999;
}
input[type="checkbox"]:active + label:before,
input[type="radio"]:active + label:before {
  transition-duration: 0;
  filter: brightness(0.2);
}
input[type="checkbox"] + label,
input[type="radio"] + label {
  position: relative;
  padding-left: 26px;
  font-weight: normal;
}
input[type="checkbox"] + label:before, input[type="checkbox"] + label:after,
input[type="radio"] + label:before,
input[type="radio"] + label:after {
  box-sizing: content-box;
  position: absolute;
  content: '';
  display: block;
  left: 0;
}
input[type="checkbox"] + label:before,
input[type="radio"] + label:before {
  top: 50%;
  width: 16px;
  height: 16px;
  margin-top: -10px;
  border: 2px solid #d9d9d9;
  text-align: center;
}
input[type="checkbox"] + label:after,
input[type="radio"] + label:after {
  background-color: #00bad2;
  top: 50%;
  left: 6px;
  width: 8px;
  height: 8px;
  margin-top: -4px;
  transform: scale(0);
  transform-origin: 50%;
  transition: transform 200ms ease-out;
}

input[type="radio"]:checked + label:before {
  -moz-animation: borderscale 300ms ease-in;
  -webkit-animation: borderscale 300ms ease-in;
  animation: borderscale 300ms ease-in;
  background-color: #fff;
}
input[type="radio"]:checked + label:after {
  transform: scale(1);
}
input[type="radio"] + label:before, input[type="radio"] + label:after {
  border-radius: 50%;
}

input[type="checkbox"] + label:after {
  background-color: transparent;
  top: 50%;
  left: 5px;
  width: 7px;
  height: 4px;
  margin-top: -5px;
  border-style: solid;
  border-color: #00bad2;
  border-width: 0 0 3px 3px;
  -moz-transform: rotate(-45deg) scale(0);
  -ms-transform: rotate(-45deg) scale(0);
  -webkit-transform: rotate(-45deg) scale(0);
  transform: rotate(-45deg) scale(0);
  -moz-transition: none;
  -o-transition: none;
  -webkit-transition: none;
  transition: none;
}
input[type="checkbox"]:checked + label:before {
  -moz-animation: borderscale 200ms ease-in;
  -webkit-animation: borderscale 200ms ease-in;
  animation: borderscale 200ms ease-in;
}
input[type="checkbox"]:checked + label:after {
  content: '';
  -moz-transform: rotate(-45deg) scale(1);
  -ms-transform: rotate(-45deg) scale(1);
  -webkit-transform: rotate(-45deg) scale(1);
  transform: rotate(-45deg) scale(1);
  -moz-transition: -moz-transform 200ms ease-out;
  -o-transition: -o-transform 200ms ease-out;
  -webkit-transition: -webkit-transform 200ms ease-out;
  transition: transform 200ms ease-out;
}

input[type="text"] {
  font-family: "Open Sans", "Helvetica Neue", Arial, sans-serif;
  font-weight: normal;
  font-size: 16px;
  line-height: 24px;
  padding: 5px 10px;
  background-color: #fff;
  border: 2px solid #d9d9d9;
}
input[type="text"]:focus {
  outline: none;
}
input[type="text"]:focus, input[type="text"]:hover {
  border-color: #999;
}



/* IE 10/11+ - This hides native dropdown button arrow so it will have the custom appearance, IE 9 and earlier get a native select - targeting media query hack via http://browserhacks.com/#hack-28f493d247a12ab654f6c3637f6978d5 - looking for better ways to achieve this targeting */

@media screen and (-ms-high-contrast: active), (-ms-high-contrast: none) {
  select::-ms-expand {
    display: none;
  }

  select:focus::-ms-value {
    background: transparent;
    color: grey;
  }
}


// ----------- 여기까지 카테고리 css --------------
</style>
</head>
<body>
<form action="">
<fieldset style="width:200px;">

    <h2>검색 조건</h2>
<!--     <div> -->
<!--         <ul> -->
<!--             <li><input id="radio1" name="radio" type="radio" class="radio" checked="checked"> <label for="radio1">Option 1</label></li> -->
<!--             <li><input id="radio2" name="radio" type="radio" class="radio"> <label for="radio2">Option 2</label></li> -->
<!--             <li><input id="radio3" name="radio" type="radio" class="radio"> <label for="radio3">Option 3</label></li> -->
<!--         </ul> -->
<!--     </div> -->
    <div>
        <ul>
        <h4>1박 평균요금</h4>
            <li><input id="checkbox1" name="checkbox" type="checkbox"> <label for="checkbox1">0 ~ 50,000</label></li>
            <li><input id="checkbox2" name="checkbox" type="checkbox"> <label for="checkbox2">50,000 ~ 100,000</label></li>
            <li><input id="checkbox3" name="checkbox" type="checkbox"> <label for="checkbox3">100,000 ~ 150,000</label></li>
             <li><input id="checkbox4" name="checkbox" type="checkbox"> <label for="checkbox4">150,000 ~ 200,000</label></li>
              <li><input id="checkbox5" name="checkbox" type="checkbox"> <label for="checkbox5">200,000+</label></li>
        </ul>
    </div>
    <hr>
     <div>
        <ul>
        <h4>호텔 성급</h4>
            <li><input id="checkbox6" name="checkbox" type="checkbox"> <label for="checkbox6">5성</label></li>
            <li><input id="checkbox7" name="checkbox" type="checkbox"> <label for="checkbox7">4성</label></li>
            <li><input id="checkbox8" name="checkbox" type="checkbox"> <label for="checkbox8">3성</label></li>
            <li><input id="checkbox9" name="checkbox" type="checkbox"> <label for="checkbox9">2성</label></li>
            <li><input id="checkbox10" name="checkbox" type="checkbox"> <label for="checkbox10">1성</label></li>
        </ul>
    </div>
    <hr>
    <div>
        <ul>
        <h4>평점</h4>
            <li><input id="checkbox11" name="checkbox" type="checkbox"> <label for="checkbox11">5</label></li>
            <li><input id="checkbox12" name="checkbox" type="checkbox"> <label for="checkbox12">4</label></li>
            <li><input id="checkbox13" name="checkbox" type="checkbox"> <label for="checkbox13">3</label></li>
            <li><input id="checkbox14" name="checkbox" type="checkbox"> <label for="checkbox14">2</label></li>
            <li><input id="checkbox15" name="checkbox" type="checkbox"> <label for="checkbox15">1</label></li>
        </ul>
    </div>
    <hr>
    <div>
        <ul>
        <h4>침대 사이즈</h4>
            <li><input id="checkbox16" name="checkbox" type="checkbox"> <label for="checkbox16">퀸 사이즈</label></li>
            <li><input id="checkbox17" name="checkbox" type="checkbox"> <label for="checkbox17">싱글 사이즈</label></li>
        </ul>
    </div>
    <hr>
    </fieldset>
</form>


</body>
</html>