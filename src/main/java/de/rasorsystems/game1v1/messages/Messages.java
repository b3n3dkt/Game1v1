package de.rasorsystems.game1v1.messages;

import de.rasorsystems.game1v1.utils.FileBuilder;

public class Messages {

    FileBuilder fileBuilder;
    String path = "plugins//Game1v1//";
    String file = "messages.yml";

    public Messages() { this.fileBuilder = new FileBuilder(path, file); }

    public boolean exist(){ return this.fileBuilder.exist(); }

    public void createNewMessagesConfig(){
        this.fileBuilder.setValue("prefix", "&bStick&fFight &7> ");
        this.fileBuilder.setValue("noperm", "&bStick&fFight &7> &cYou don't have permissions to do that!");
        this.fileBuilder.setValue("youareconsole", "You are the console");
        this.fileBuilder.setValue("unknowncommand", "&bStick&fFight &7>  &cThe command &8'&e%unknowncommand%&8' &cdoes not exist!");
        this.fileBuilder.setValue("spawn.setspawn", "&bStick&fFight &7> &aYou succesfully created the spawn!");
        this.fileBuilder.setValue("spawn.cooldown", "&bStick&fFight &7> &cPlease wait a couple of seconds before using the command again!");
        this.fileBuilder.setValue("spawn.teleported", "&bStick&fFight &7> &aYou have been teleported to the spawn!");
        this.fileBuilder.setValue("login.mysqldisconnected", "&bStick&fFight &7> &cError in Database,\n &cplease wait a few seconds!");
        this.fileBuilder.setValue("join.joinmessage", "&8[&a+&8] &7%player%");
        this.fileBuilder.setValue("join.welcomemessage", "\n&7Welcome to &bStickFight" +
                                                                         "\n&7If there are any bugs or something does not work properly," +
                                                                         "\n&7you can contact us via &bDiscord&7!" +
                                                                         "\n&7Thank you and have fun!\n");
        this.fileBuilder.setValue("leave.leavemessage", "&8[&c-&] &7%player%");
        this.fileBuilder.save();
    }

    public String getMessage(String valuePath){ return this.fileBuilder.getString(valuePath).replace("&", "§");}

    public String getPrefix(){ return getMessage("prefix"); }
    public String getNoperm(){ return getMessage("noperm"); }

}
