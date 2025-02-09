package net.crystopia.crystopiacloud.mesages

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

class MessageCommandsMessages {

    val mm = MiniMessage.miniMessage()

    val discordMessage = mm.deserialize(
        "\n<color:#7187D6><b>Discord</b> <gray>▪</gray> <white>Join Our Discord Community!</white></color>\n" + "\n<color:#96A4D3>Hey! Are you enjoying your time on the server?Become more engaged with our <white>community</white>,</color>\n" + "<color:#96A4D3>by joining our discord server!</color>\n" + "           \n" + "<color:#7187D6>                 <click:open_url:'https://crystopia.link/discord'>Join our Discord</click></color>\n"
    )
    val bugMessage: Component = mm.deserialize(
        "\n <color:#ffaded><b>Bug Reports</b></color> <gray>▪</gray> <white>Submit a Bug Report</white>\n" + "\n            \n" + "\n<color:#aeff2b>To submit a bug, join our <white><click:run_command:'/discord'>Discord</click></white> and go to #bug-tracker to submit your report.</color>\n" + "    \n" + "\n<red><b>Note</b>: Please submit images and all information you have.</red>\n"
    )
    val guidesMessage: Component = mm.deserialize(
        "\n <b><color:#abfff8>Guides</color></b> <gray>▪</gray> <white>Grystopia Help</white>\n" + "\n<color:#abfff8>Hii, and welcome to the <white><click:open_url:'https://crystopia.link/guides'>Guides</click></white>.\nWe have on our website a page for all <white><click:open_url:'https://crystopia.link/guides'>Guides</click></white>\n" + "to help you with the features. And by all <white><click:open_url:'https://crystopia.link/guides'>Features</click></white> you find an icon to get the link.</color>\n" + "\n" + "                 <click:open_url:'https://guides.crystopia.link'>Go to the Guides</click>\n"
    )
    val helpMessage: Component = mm.deserialize(
        "\n<click:open_url:'https://guides.crystopia.link'><color:#47c8ff>You not know how to or have a problem then use our Guides<gray> (Click the Message)</gray></color></click>\n"
    )
    val storeMessage: Component = mm.deserialize(
        """
            <b><color:#26fff1>Store</color></b> <gray>▪</gray> <white>Crystopia Shop</white>
            
            <color:#26fff1>Hello! If you'd like to support us, you can use 
            /vote to promote the server. We currently do not have a real-money shop.</color>
            """.trimIndent()
    )
    val voteMessage: Component = mm.deserialize(
        " <b><color:#b06bff>Vote</color></b> <gray>▪</gray> <white>Vote for the Server</white>\n" +
                "\n<color:#b06bff>Cool! You want to promote our server on the \n" +
                "Vote Pages? This is so cool. Use the links \n" +
                "below to get to the sites:</color>        \n" +
                "\n<dark_gray>-</dark_gray> <click:open_url:'https://crystopia.link/vote1'><white>Vote 1<gray>:</gray> minecraft-server.eu</white></click>\n" +
                "<dark_gray>-</dark_gray><click:open_url:'https://crystopia.link/vote2'><white> Vote 2<gray>:</gray> planetminecraft.com</white></click>\n<dark_gray>-</dark_gray> <click:open_url:'https://crystopia.link/vote3'><white>Vote 3<gray>:</gray> polymart.org</white></click>\n"
    )
}