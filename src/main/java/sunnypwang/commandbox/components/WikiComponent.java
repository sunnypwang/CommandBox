package sunnypwang.commandbox.components;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sunnypwang.commandbox.CommandBox;

import java.io.IOException;

public class WikiComponent implements CommandExecutor {

    private final CommandBox instance;

    public WikiComponent(CommandBox instance) {
        this.instance = instance;
        this.instance.getCommand("wiki").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String url_str = "https://minecraft.gamepedia.com/";
        if (args.length > 0) {
            url_str += String.join("_", args);

            //get Jsoup Document
            Document doc = null;
            try {
                doc = Jsoup.connect(url_str).get();
            } catch (IOException e) {
//                System.out.println("404 Not Found");
                sender.sendMessage( ChatColor.RED + "404 Not Found");
                return true;

            }

            //get general description
            String generalDesc = null;
            try {
                Element elementDesc = doc.select("meta[name='description']").first();
                generalDesc = elementDesc.attr("content").replaceFirst("\\..*", "");
            } catch (Exception e) {
                e.printStackTrace();
            }

            //get specific description (block usage / mob behavior)
            String desc = "";
            try {
                //try getting specific id, if it fails, it should throw NullPointerException
                Element elementUsage = null;
                if (doc.getElementById("Usage") != null) {
                    elementUsage = doc.getElementById("Usage");
                } else if (doc.getElementById("Behavior") != null) {
                    elementUsage = doc.getElementById("Behavior");
                } else {
                    System.out.println("Cannot retrieve extra info");
                }
                //Traverse DOM document to get description paragraph
                Element elementUsageParent = elementUsage.parent();
                int idx = elementUsageParent.elementSiblingIndex();
                Elements children = elementUsageParent.parent().children();
                Element elementUsageParagraph = children.get(idx + 1);
                //search for next paragraph tag
                while (!elementUsageParagraph.tagName().equals("p") && idx < children.size() - 1) {
                    idx++;
                    elementUsageParagraph = children.get(idx);
                }
                desc = elementUsageParagraph.text();
            } catch (NullPointerException e) {
                System.out.println(e.toString());
            }

            //combine message and send
            String msg = String.join("\n", ChatColor.AQUA + url_str + ChatColor.RESET, generalDesc, desc);
            sender.sendMessage(msg);
//            System.out.println(msg);


        } else { //if no arguments, simply print home URL
            sender.sendMessage(ChatColor.AQUA + url_str);
//            System.out.println(url_str);
        }
        return true;
    }
}
