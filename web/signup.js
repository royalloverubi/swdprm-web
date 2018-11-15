/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function checkPass()
{
    //Store the password field objects into variables ...
    var pass1 = document.getElementById('password');
    var pass2 = document.getElementById('pass2');
    //Store the Confimation Message Object ...
    var message = document.getElementById('confirmMessage');
    //Set the colors we will be using ...
    var goodColor = "#66cc66";
    var badColor = "#ff6666";
    //Compare the values in the password field 
    //and the confirmation field
    if(pass1.value == pass2.value){
        //The passwords match. 
        //Set the color to the good color and inform
        //the user that they have entered the correct password 
        pass2.style.backgroundColor = goodColor;
        message.style.color = goodColor;
        message.innerHTML = "Passwords Match"
    }else{
        //The passwords do not match.
        //Set the color to the bad color and
        //notify the user.
        pass2.style.backgroundColor = badColor;
        message.style.color = badColor;
        message.innerHTML = "Passwords Do Not Match!"
    }
} 
// validates text only
function Validate(txt) {
    txt.value = txt.value.replace(/[^a-zA-Z-'\n\r.]+/g, '');
}
// validate email
function email_validate(email)
{
var regMail = /^([_a-zA-Z0-9-]+)(\.[_a-zA-Z0-9-]+)*@([a-zA-Z0-9-]+\.)+([a-zA-Z]{2,3})$/;

    if(regMail.test(email) == false)
    {
    document.getElementById("status").innerHTML    = "<span class='warning'>Email address is not valid yet.</span>";
    }
    else
    {	
    }
}
// validate address
function add_validate(address)
{
var regAdd = /^(?=.*\d)[a-zA-Z\s\d\/]+$/;
  
    if(regAdd.test(address) == false)
    {
    document.getElementById("statusAdd").innerHTML	= "<span class='warning'>Address is not valid yet.</span>";
    }
    else
    {
    	
    }
}
function summit(){
    var number = $('#phone').val();
    var name = $('#name').val();
    var email = $('#email').val();
    var password = $('#password').val();
       
    var lat = "10.8523438";
    var long = "106.6268788";
    
    var role ="cyber";
    var active = "true";
    var deleted = "false";
    
    var AccountDTO = {
           active: active,
           deleted: deleted,
           username: email,
           role: role,
           password: password
       };
       var originalURL = "https://swd-backend-lamtt.herokuapp.com/account";
       var queryURL = "https://cors-anywhere.herokuapp.com/"
    $.ajax({
           type: "POST",
           url: queryURL+originalURL,
           dataType: "json",
           data: JSON.stringify(AccountDTO),
           contentType: 'application/json; charset=utf-8',
           headers: {
                "x-requested-with": "xhr" 
            },
           success: function (msg) {
               if (msg) {
                   createCustomer(msg.username,msg.id,name,number);
                   createCyber(msg.id,"",lat,long,name);
                   login(msg.username,msg.password);
               }
           },
           statusCode: {
            500: function() {
              alert( "email is exist" );
            }
          },
       });
}
function createCustomer(email,accountId,name,phone){
    var customerDTO = {
        accountId: accountId,
        active: "true",
        avatar: "",
        deleted: "false",
        email: email,
        name: name,
        phone: phone,
        sex: 1
      };
    var queryURL = "https://cors-anywhere.herokuapp.com/"
    var originCreateCust = "https://swd-backend-lamtt.herokuapp.com/customer";
    $.ajax({
           type: "PUT",
           url: queryURL+originCreateCust,
           dataType: "json",
           data: JSON.stringify(customerDTO),
           contentType: 'application/json; charset=utf-8',
           headers: {
                "x-requested-with": "xhr" 
            },
           success: function (msg) {
               if (msg) {
                   
               }
           },
       });
}
function createCyber(accountId,address,latitude,logitude,name){
  var  cyberDTO ={
  accountId: accountId,
  active: true,
  address: address,
  deleted: false,
  latitude: latitude,
  logitude: logitude,
  logo: "https://image.ibb.co/eZasF0/icon-shop-detail.jpg",
  name: name,
  numberOfEvaluator: 5,
  numberOfStar:1
};
var queryURL = "https://cors-anywhere.herokuapp.com/"
    var originCreateCust = "https://swd-backend-lamtt.herokuapp.com/cyber";
    $.ajax({
           type: "POST",
           url: queryURL+originCreateCust,
           dataType: "json",
           data: JSON.stringify(cyberDTO),
           contentType: 'application/json; charset=utf-8',
           headers: {
                "x-requested-with": "xhr" 
            },
           success: function (msg) {
               if (msg) {
                   
               }
           },
       });
}
function login(username,password){
    var query = "http://localhost:8084/CyberGaming/login";
    $.ajax({
           type: "POST",
           url: query,
           dataType: "json",
           data: {username:username,password:password},
           //contentType: 'application/json; charset=utf-8',
           headers: {
                "x-requested-with": "xhr" 
            },
           success: function (msg) {
               
           },error: function(xhr, textStatus, errorThrown){
                
           },complete: function(data) {
            location.href = "http://localhost:8084/CyberGaming/cyber.action";
        }
       });
}



