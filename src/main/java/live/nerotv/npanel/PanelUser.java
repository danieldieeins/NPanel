package live.nerotv.npanel;

public record PanelUser(String password, boolean canEditFiles, boolean canChangeGroups, boolean canSendCommands) {

}