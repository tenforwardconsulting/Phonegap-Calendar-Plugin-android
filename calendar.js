

function calendarPlugin()
{
}

calendarPlugin.prototype.createEvent = function(title,location,notes,startDate,endDate, successCallback, errorCallback) {
    if (typeof errorCallback != "function")  {
        console.log("calendarPlugin.createEvent failure: errorCallback parameter must be a function");
        return
    }
    
    if (typeof successCallback != "function") {
        console.log("calendarPlugin.createEvent failure: successCallback parameter must be a function");
        return
    }
    cordova.exec(
      successCallback, // called when signature capture is successful
      errorCallback, // called when signature capture encounters an error
      'CalendarPlugin', // Tell cordova that we want to run "PushNotificationPlugin"
      'addToCalendar', // Tell the plugin the action we want to perform
      [{
        "title": title,
        "description": notes,
        "eventLocation": location,
        "startTimeMillis": startDate.getTime(),
        "endTimeMillis": endDate.getTime()
      }]
    ); // List of arguments to the plugin
};
calendarPlugin.prototype.deleteEvent = function(title,location,notes,startDate,endDate, deleteAll, successCallback, errorCallback) {
    throw "NotImplemented";
}

calendarPlugin.prototype.findEvent = function(title,location,notes,startDate,endDate, successCallback, errorCallback) {
    throw "NotImplemented";
}

calendarPlugin.prototype.modifyEvent = function(title,location,notes,startDate,endDate, newTitle, newLocation, newNotes, newStartDate, newEndDate, successCallback, errorCallback) {
    throw "NotImplemented";
}


calendarPlugin.install = function()
{
    if(!window.plugins)
    {
        window.plugins = {};
    }
    
    window.plugins.calendarPlugin = new calendarPlugin();
    return window.plugins.calendarPlugin;
};


cordova.addConstructor(calendarPlugin.install);