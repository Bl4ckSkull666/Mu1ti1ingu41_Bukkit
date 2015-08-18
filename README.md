Short Information : All Classes are on path de.bl4ckskull666.mu1ti1ingu41


How can i use the language?

First Step :
- Add in your plugin.yml : depend: [Mu1ti1ingu41]


Second Step :
- Set in your Main Class into the loading class like onEnable() anywhere this :
- Mu1ti1ingu41.loadExternalDefaultLanguage(this, "languages");


Step Three :
Load all messages in your Plugin with :
- A simple BaseComponent[] --> ProxiedPlayer.sendMessage(Language.getMessage(YourPlugin, Player UniqueId, Path in language file, Default message));
- A simple BaseComponent[] with replaces --> ProxiedPlayer.sendMessage(Language.getMessage(YourPlugin, Player UniqueId, Path in language file, Default message, String[] {"search1","search2","..."}, String[] {"replacewith1","replacewith2","replacewith3"));
- Get a String Array from List --> String[] moreMessages = Language.getMessages(YourPlugin, Player UniqueId, Path in language file);
- Get The Configuration File --> Configuration language = Language.getMessageFile(YourPlugin, Player UniqueId);



If you want to give out the language of a player , you can get it by :
- String myLang = UUIDLanguages.getPlayerLanguage(Player UniqueId);


How can i use Configuration Support?
First Step :
Add in your plugin.yml :
- depend: [Mu1ti1ingu41]

Seond Step :
Now you can create the variable "private Configuration _config;" and add in the onEnable() this :
- _config = Mu1ti1ingu41.loadConfig(this);
Now you can use and work with _config ;-)

To save it on disable or where ever , write:
- Mu1ti1ingu41.saveConfig(_config, this);
