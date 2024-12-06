# NPanel `BETA`
A plugin based control panel for Spigot / Paper 1.21+ servers<br>
**Downloads:** [Latest release](https://github.com/danieldieeins/NPanel/releases/latest/) | [All releases](https://github.com/danieldieeins/NPanel/releases/)

### What is this?
NPanel is a plugin based control panel for your minecraft server. It is viewable within a web browser, and does not require running any external web servers, or knowledge of PHP or databases. This plugin is in beta, and has a number of key features, including:

* A live view of the server console
* Live view of the server ram usage, cpu usage and ticks per second
* User permissions
* Modify player groups (requires vault)
* Easily kick or ban online players (not currently implemented in new player manager)
* Manage server files
* Passwords hashed with a salt

### Before installing
THIS PLUGIN REQUIRES JAVA 21 OR HIGHER. ALSO BE AWARE IT'S STILL IN BETA, AND THERE MIGHT BE BUGS OR INCOMPLETE FEATURES.

### Installing
Since this plugin doesn't require anything else other than the Spigot / Paper server, installation is extremely easy

1. Put the plugin jar file in your plugins folder
2. Restart the server (DO NOT RELOAD)
3. Ensure that ports 4567 and 9003 are port forwarded (this can be tested with [this tool here](https://www.canyouseeme.org/))
4. In the server console, use /addlogin <username> <password> to add a user to the panel
5. OPTIONAL: Stop the server and edit the config file to allow you access to the NPanel features

### For Developers
As of NPanel 2024.12-beta.1, you can now add pages to the panel. For more information, please [see the guide](https://nerotv.live/d/5-add-pages).

### Commands
All commands can only be executed through the console, either via NPanel or a standard minecraft console.

* /addlogin <username> <passsword> - allows you to add a user to a panel
* /passwd <username> <oldpassword> <newpassword> - change the password of a user

### Panel Permissions
These are modified through the config file. Once modified, restart the server.
* canEditFiles - allows a NPanel user to edit files
* canChangeGroups - allows a NPanel user to change the groups of a user
* canSendCommands - allows a NPanel user to send commands through the console

### This repository is forked from jmurth1234's JPanel
Unfortunately the project has been inactive for years, but if you still want to take a look at it, take a look here: [jmurth1234 - JPanel](https://github.com/jmurth1234/JPanel)
