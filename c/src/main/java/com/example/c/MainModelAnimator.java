package com.example.c;

import com.example.c.controllers.Table;
import javafx.scene.control.TextField;
import main.org.example.main.Response;
import main.org.example.main.TypeOfAnswer;
import main.org.example.models.StudyGroup;

public class MainModelAnimator {
    private final Table sgTableModel;
    private final FrameHandler frameHandler;
    private final TextField clientInfo;
    private final TextField serverInfo;

    public MainModelAnimator(FrameHandler aFrameHandler, TextField aClientInfo, TextField aServerInfo) {
        sgTableModel = Table.getInstance();
        frameHandler = aFrameHandler;
        clientInfo = aClientInfo;
        serverInfo = aServerInfo;
    }

    public void animateShow(Response aResponse) {
        if (aResponse.getStudyGroup() != null) {
//            sgTableModel.clearClick();
//            aResponse.getStudyGroup().forEach(sgTableModel::addData);
//            sgTableModel.fireTableDataChanged();
        }
    }

    public void animate(Response aResponse) {

        clientInfo.setText("");
        serverInfo.setText("");

        if (aResponse.getStatus().equals(TypeOfAnswer.SUCCESSFUL)) {
            clientInfo.setText("Command executed successfully!");
            serverInfo.setText("Command executed successfully!");
            StringBuilder sb = new StringBuilder();

            if (aResponse.getInformation() != null) {
                if (aResponse.getInformation().get("1") == null) {
                    aResponse.getInformation().forEach((key, value) ->
                            sb.append(key.toUpperCase())
                                    .append(" : ")
                                    .append(value.toUpperCase())
                                    .append("\n"));
                } else {
                    aResponse.getInformation().keySet().stream()
                            .map(Integer::parseInt)
                            .sorted(Integer::compareTo)
                            .map(String::valueOf)
                            .forEach(key -> sb.append(key.toUpperCase())
                                    .append(" : ")
                                    .append(aResponse.getInformation().get(key).toUpperCase())
                                    .append("\n"));
                }
//                frameHandler.printInfo(sb.toString());
            } else if (aResponse.getStudyGroup() != null) {
//                sgTableModel.clearClick();
//                aResponse.getStudyGroup().forEach(sgTableModel::addData);
//                frameHandler.stopSynchronize();
//                sgTableModel.fireTableDataChanged();
            } else if (aResponse.getStudyGroup() != null) {
//                sgTableModel.clearClick();
//                StudyGroup newStudyGroup = aResponse.getStudyGroup();
//                sgTableModel.addClick(newStudyGroup);
//                frameHandler.stopSynchronize();
//                sgTableModel.fireTableDataChanged();
            } else if (aResponse.getCount() != null) {
                sb.append("Amount of elements: ")
                        .append(aResponse.getCount())
                        .append("\n");
                serverInfo.setText(sb.toString());
            }
        } else {
            switch (aResponse.getStatus()) {
                case OBJECTNOTEXIST:
                    clientInfo.setText("Command executed successfully!");
                    serverInfo.setText("No object was found!");
                    break;
                case DUPLICATESDETECTED:
                    clientInfo.setText("Command executed successfully!");
                    serverInfo.setText("This element probably duplicates an existing one and can't be added");
                    break;
                case ISNTMAX:
                    clientInfo.setText("Command executed successfully!");
                    serverInfo.setText("Study group isn't max!");
                    break;
                case PERMISSIONDENIED:
                    clientInfo.setText("Command executed successfully!");
                    serverInfo.setText("Permission denied!");
                    break;
                case SQLPROBLEM:
                    clientInfo.setText("Command executed successfully!");
                    serverInfo.setText("Some problems with the database!");
                    break;
                case EMPTYCOLLECTION:
                    clientInfo.setText("Command executed successfully!");
                    serverInfo.setText("Collection is empty!");
                    break;
                case ALREADYREGISTERED:
                    clientInfo.setText("Command executed successfully!");
                    serverInfo.setText("This account is already registered!");
                    break;
                case NOTMATCH:
                    clientInfo.setText("Command executed successfully!");
                    serverInfo.setText("An account with these parameters doesn't exist!");
                    break;
                case COMMANDNOTGO:
                    clientInfo.setText("Command didn't go to the server!");
                    break;
                case ANOTHERVERSION:
                    clientInfo.setText("Server version is higher!");
                    break;
                case NETPROBLEM:
                    clientInfo.setText("Some problems with the internet!");
                    break;
                case SERVERNOTAVAILABLE:
                    clientInfo.setText("Server not available at the moment!");
                    break;
                case NOTSERIALIZED:
                    clientInfo.setText("Command cannot be serialized!");
                    break;
                case NOTVALIDATE:
                    clientInfo.setText("Data in fields is incorrect!");
                    break;
                case RECURSIONDETECTED:
                    clientInfo.setText("Recursion detected!");
            }
        }
    }
}
