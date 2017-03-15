var exec = require('cordova/exec');

function SurprisePlugin() { 
 console.log("SurprisePlugin is created");
}

SurprisePlugin.prototype.showToast = function(aString){
 console.log("SurprisePlugin: showToast");

 exec(function(result){
     /*alert("OK" + reply);*/
   },
  function(result){
    /*alert("Error" + reply);*/
   },"SurprisePlugin",aString,[]);
}

 var surprise = new SurprisePlugin();
 module.exports = surprise;
