import java.util.ArrayList;
import java.util.Scanner;
import java.util.LinkedList;

public class SocialNetwork 
{
    public static void main(String[] args) 
    {
        LinkedList<node> Nodes = new LinkedList<>();
        Scanner myObj = new Scanner(System.in);

        int choice = 0;
        do{
            System.out.println("\n\nMENU: ");
            System.out.println("1: To CREATE a NODE");
            System.out.println("2: To DELETE  a node");
            System.out.println("3: SEARCH for a node");
            System.out.println("4: To print all linked nodes to an input node");
            System.out.println("5: To create and post content by a user");
            System.out.println("6: To search for content by any user");
            System.out.println("7: To Display all content posted by nodes linked to a given node");
            System.out.println("8: To print all the nodes");
            System.out.println("-1: Quit");

            choice = myObj.nextInt();
            myObj.nextLine();

            switch(choice)
            {
                case 1:
                    String myInput = "Y";
            
                    //System.out.println("*****/To take input/*****");
            
                    while(!myInput.equals("N"))
                    {
                        Nodes.add(CreateNode(myObj));
                        System.out.println("Do you want more data?(Y/N) ");
                        myInput = myObj.nextLine();
                    }
                    break;
                
                case 2:
                    //System.out.println("**********/To test the delete Node Function/***********");
                    System.out.println("Enter the id of the node to be deleted: ");
            
                    String index = myObj.nextLine();
                    DeleteNode(index, Nodes);
                    System.out.println("New List of nodes: ");
                    PrintAllNodes(myObj, Nodes);
                    myObj.nextLine();
                    break;
                
                case 3:
                    //System.out.println("********/To test the Search for node by name or birthday or type function/********");
                    SearchForNode(myObj, Nodes);
                    break;
                
                case 4:
                    //System.out.println("*********/To test the function to print all linked nodes to an input node/********");
                    PrintLinkedNodes(myObj, Nodes);
                    break;
                
                case 5:
                    System.out.println("**********/To test the function to create and post content by a user/***********");
                    System.out.println("Enter the ID of the user to create content: ");
                    String tempID = myObj.nextLine();
                    int i=0;
                    //myObj.nextLine();

                    for (i = 0; i < Nodes.size(); i++) 
                    {
                        node temp = Nodes.get(i);
                        if (tempID.equals(temp.id))
                            break;
                    }

                    if(i == Nodes.size()) System.out.println("Invalid Input");

                    node temp = Nodes.get(i);
                    temp.CreateContent(myObj);
                    break;
                
                case 6:
                    //System.out.println("**********/To test the function to search for content by any user/***********");
                    SearchContent(myObj, Nodes);
                    break;
                
                case 7:
                    //System.out.println("***/To test the function to Display all content posted by nodes linked to a given node/***");
                    PrintContentOfLinkedNodes(myObj, Nodes);
                    break;   
                
                case 8:
                    //System.out.println("**********/To print all the nodes/***********");
                    PrintAllNodes(myObj, Nodes);
                    break;
                
                case -1:
                    System.out.println("**********/THE END/***********");
                    break;         
            }

        }while(choice != -1);

    }

    static void PrintAllNodes(Scanner myObj, LinkedList<node> Node)
    {
        System.out.println("All the users(nodes) are: ");
        if(Node.size() == 0) System.out.println("No nodes.");

        else
        {
            for(int i=0; i<Node.size(); i++)
            {
                System.out.println("User No. " + i +":");
                node temp = Node.get(i);
                temp.printNode();
            }
        }
    }

    //static node CreateNode(Scanner myObj)
    static node CreateNode(Scanner myObj)
    {
        System.out.println("Enter the type (individual/business/organisation/group): ");
        String myType = myObj.nextLine();
        if(myType.equals("individual")) return new Individuals(myObj);
        else if(myType.equals("business")) return new businesses(myObj);
        else if(myType.equals("organisation")) return new organisations(myObj);
        else if(myType.equals("group")) return new groups(myObj);
        else return null;
    }

    static void DeleteNode(String id, LinkedList<node> Node) 
    {   
        int i=0;

        for (i = 0; i < Node.size(); i++) 
        {
            node temp = Node.get(i);
            if (id.equals(temp.id))
                break;
        }

        if(i == Node.size()) System.out.println("Invalid Input");

        else Node.remove(i);
    }

    static void SearchForNode(Scanner myObj, LinkedList<node> Node) 
    {
        System.out.println("Search by what criteria?(name/type/birthday)");
        String myCriteria = myObj.nextLine();

        String myInput = "NULL";

        if (myCriteria.equals("name")) 
        {
            System.out.println("Enter the name to be searched for: ");
            myInput = myObj.nextLine();
            for (int i = 0; i < Node.size(); i++) 
            {
                node temp = Node.get(i);
                if (myInput.equals(temp.name))
                    System.out.println("The unique id: " + temp.id);
            }
        }

        else if (myCriteria.equals("type")) 
        {
            System.out.println("Enter the type to be searched for: ");
            myInput = myObj.nextLine();
            for (int i = 0; i < Node.size(); i++) 
            {
                node temp = Node.get(i);
                if (myInput.equals(temp.type))
                    System.out.println("The unique id: " + temp.id);
            }
        }

        else if (myCriteria.equals("birthday")) 
        {
            System.out.println("Enter the birthday to be searched for: ");
            myInput = myObj.nextLine();
            for (int i = 0; i < Node.size(); i++) 
            {
                node temp = Node.get(i);
                if (temp.type.equals("individual")) 
                {
                    Individuals temp2 = (Individuals) Node.get(i);
                    if (myInput.equals(temp2.birthday))
                        System.out.println("The unique id: " + temp.id);
                }
            }
        }
        
        else 
        {
            System.out.println("Invalid Input");
            SearchForNode(myObj, Node);
        }

        System.out.println("Do you want to search more?(Y/N)");
        String mySopu = myObj.nextLine();
        if(mySopu.equals("Y")) SearchContent(myObj, Node);
    }

    static void SearchContent(Scanner myObj, LinkedList<node> Node) 
    {
        System.out.println("Enter the content to be searched for: ");
        String myInput = myObj.nextLine();
        for (int i = 0; i < Node.size(); i++) 
        {
            node temp = Node.get(i);
            for (int j = 0; j < temp.content.size(); j++) 
            {
                String tempStr = temp.content.get(j);
                if (myInput.equals(tempStr)) 
                {
                    System.out.println("The unique id: " + temp.id);
                }
            }
        }
    }

    static void PrintLinkedNodes(Scanner myObj, LinkedList<node> Node)
    {
        System.out.println("Enter the ID of the user to print the linked nodes: ");

        String myInput = myObj.nextLine();
        int i=0;

        for (i = 0; i < Node.size(); i++) 
        {
            node temp = Node.get(i);
            if (myInput.equals(temp.id))
                break;
        }

        if(i == Node.size()) System.out.println("Invalid Input");

        else
        {
            node temp = Node.get(i);
            if(temp.type.equals("individual"))
            {
                Individuals temp2 = (Individuals)temp;

                for(int j=0; j< temp2.isCustomer.size(); j++)
                {
                    System.out.println(temp2.isCustomer.get(j));
                }

                for(int j=0; j< temp2.isMember.size(); j++)
                {
                    System.out.println(temp2.isMember.get(j));
                }

                for(int j=0; j< temp2.isOwner.size(); j++)
                {
                    System.out.println(temp2.isOwner.get(j));
                }
            }

            if(temp.type.equals("business"))
            {
                businesses temp2 = (businesses)temp;

                for(int j=0; j< temp2.Customers.size(); j++)
                {
                    System.out.println(temp2.Customers.get(j));
                }

                for(int j=0; j< temp2.Owners.size(); j++)
                {
                    System.out.println(temp2.Owners.get(j));
                }

                for(int j=0; j< temp2.isAMember.size(); j++)
                {
                    System.out.println(temp2.isAMember.get(j));
                }
            }

            if(temp.type.equals("group"))
            {
                groups temp2 = (groups)temp;

                for(int j=0; j< temp2.Members.size(); j++)
                {
                    System.out.println(temp2.Members.get(j));
                }
            }  

            if(temp.type.equals("organisation"))
            {
                organisations temp2 = (organisations)temp;

                for(int j=0; j< temp2.Memberss.size(); j++)
                {
                    System.out.println(temp2.Memberss.get(j));
                }
            }
        }  
        
    }

    static void PrintContentOfLinkedNodes(Scanner myObj, LinkedList<node> Node)
    {
        System.out.println("Enter the ID of the user to print the content of the linked nodes: ");

        String myInput = myObj.nextLine();
        int i=0;

        for (i = 0; i < Node.size(); i++) 
        {
            node temp = Node.get(i);
            if (myInput.equals(temp.id))
                break;
        }

        if(i == Node.size()) System.out.println("Invalid Input");

        else{
            node temp = Node.get(i);
            if(temp.type.equals("individual"))
            {
                Individuals temp2 = (Individuals)temp;

                for(int j=0; j< temp2.isCustomer.size(); j++)
                {
                    String demo = temp2.isCustomer.get(j);
                    int k=0;
                    //int demoId = SearchContent(myObj, Node);
                    for (k = 0; k < Node.size(); k++) 
                    {
                        node tem = Node.get(k);
                        if (demo.equals(tem.name))
                            break;
                    }

                    node temp3 = Node.get(k);
                    if(temp3.content.size() == 0) System.out.println("There is no content");
                    else 
                    {
                        System.out.println("The content posted by " + temp3.name + " is: ");
                        System.out.println(temp3.content);
                    }
                    
                }

                for(int j=0; j< temp2.isMember.size(); j++)
                {   
                    String demo = temp2.isMember.get(j);
                    int k=0;
                    //int demoId = SearchContent(myObj, Node);
                    for (k = 0; k < Node.size(); k++) 
                    {
                        node tem = Node.get(k);
                        if (demo.equals(tem.name))
                            break;
                    }

                    node temp3 = Node.get(k);
                    if(temp3.content.size() == 0) System.out.println("There is no content");
                    else 
                    {
                        System.out.println("The content posted by " + temp3.name + " is: ");
                        System.out.println(temp3.content);
                    }
                    
                }

                for(int j=0; j< temp2.isOwner.size(); j++)
                {
                    String demo = temp2.isOwner.get(j);
                    int k=0;
                    //int demoId = SearchContent(myObj, Node);
                    for (k = 0; k < Node.size(); k++) 
                    {
                        node tem = Node.get(k);
                        if (demo.equals(tem.name))
                            break;
                    }

                    node temp3 = Node.get(k);
                    if(temp3.content.size() == 0) System.out.println("There is no content");
                    else 
                    {
                        System.out.println("The content posted by " + temp3.name + " is: ");
                        System.out.println(temp3.content);
                    }
                }
            }

            if(temp.type.equals("business"))
            {
                businesses temp2 = (businesses)temp;

                for(int j=0; j< temp2.Customers.size(); j++)
                {
                    String demo = temp2.Customers.get(j);
                    int k=0;
                    //int demoId = SearchContent(myObj, Node);
                    for (k = 0; k < Node.size(); k++) 
                    {
                        node tem = Node.get(k);
                        if (demo.equals(tem.name))
                            break;
                    }

                    node temp3 = Node.get(k);
                    if(temp3.content.size() == 0) System.out.println("There is no content");
                    else 
                    {
                        System.out.println("The content posted by " + temp3.name + " is: ");
                        System.out.println(temp3.content);
                    }
                    
                }

                for(int j=0; j< temp2.Owners.size(); j++)
                {
                    String demo = temp2.Owners.get(j);
                    int k=0;
                    //int demoId = SearchContent(myObj, Node);
                    for (k = 0; k < Node.size(); k++) 
                    {
                        node tem = Node.get(k);
                        if (demo.equals(tem.name))
                            break;
                    }

                    node temp3 = Node.get(k);
                    if(temp3.content.size() == 0) System.out.println("There is no content");
                    else 
                    {
                        System.out.println("The content posted by " + temp3.name + " is: ");
                        System.out.println(temp3.content);
                    }
                    
                }

                for(int j=0; j< temp2.isAMember.size(); j++)
                {
                    String demo = temp2.isAMember.get(j);
                    int k=0;
                    //int demoId = SearchContent(myObj, Node);
                    for (k = 0; k < Node.size(); k++) 
                    {
                        node tem = Node.get(k);
                        if (demo.equals(tem.name))
                            break;
                    }

                    node temp3 = Node.get(k);
                    if(temp3.content.size() == 0) System.out.println("There is no content");
                    else 
                    {
                        System.out.println("The content posted by " + temp3.name + " is: ");
                        System.out.println(temp3.content);
                    }
                    
                }

            }

            if(temp.type.equals("group"))
            {
                groups temp2 = (groups)temp;

                for(int j=0; j< temp2.Members.size(); j++)
                {
                    String demo = temp2.Members.get(j);
                    int k=0;
                    //int demoId = SearchContent(myObj, Node);
                    for (k = 0; k < Node.size(); k++) 
                    {
                        node tem = Node.get(k);
                        if (demo.equals(tem.name))
                            break;
                    }

                    node temp3 = Node.get(k);
                    if(temp3.content.size() == 0) System.out.println("There is no content");
                    else 
                    {
                        System.out.println("The content posted by " + temp3.name + " is: ");
                        System.out.println(temp3.content);
                    }
                    
                }
            }

            if(temp.type.equals("organisation"))
            {
                organisations temp2 = (organisations)temp;

                for(int j=0; j< temp2.Memberss.size(); j++)
                {
                    String demo = temp2.Memberss.get(j);
                    int k=0;
                    //int demoId = SearchContent(myObj, Node);
                    for (k = 0; k < Node.size(); k++) 
                    {
                        node tem = Node.get(k);
                        if (demo.equals(tem.name))
                            break;
                    }

                    node temp3 = Node.get(k);
                    if(temp3.content.size() == 0) System.out.println("There is no content");
                    else 
                    {
                        System.out.println("The content posted by " + temp3.name + " is: ");
                        System.out.println(temp3.content);
                    }
                    
                }
            }
        }

    }
    
}


class node
{
    String id;
    String name;
    String creationDate;    //DDMMYYYY format
    ArrayList<String> content = new ArrayList<>();
    //ArrayList<linkObj> LinkSet = new ArrayList<>();
    String type;

    public node(Scanner myObj)
    {
        // myObj = new Scanner(System.in);
        System.out.println("Enter the name: ");
        this.name = myObj.nextLine();
        System.out.println("Enter the Creation Date in (DDMMYYYY): ");
        this.creationDate = myObj.nextLine();
        CreateContent(myObj);

    }

    void CreateContent(Scanner myObj)
    {
        System.out.println("Enter the content posted by the user: ");
        content.add(myObj.nextLine());
        while(true)
        {
            System.out.println("Is there more content?(Yes/No)");
            String temp = myObj.nextLine();
            if(temp.equals("No")) break;
            else
            {
                System.out.println("Enter the content posted by the user: ");
                content.add(myObj.nextLine());
            }
        }
    }

    void printNode()
    {
        System.out.println("Unique ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("User type: " + type);
        System.out.println("Creation Date: " + creationDate);
    }
}

class Individuals extends node
{ 
    static int IDindex = 0;
    String birthday = "Not given";
    ArrayList<String> isMember = new ArrayList<>();
    ArrayList<String> isOwner = new ArrayList<>();
    ArrayList<String> isCustomer = new ArrayList<>();

    public Individuals(Scanner myObj)
    {
        super(myObj);
        type = "individual";
        System.out.println("Do you want give a birthday?(Yes/No)");
        String bdayInput = myObj.nextLine();
        if(bdayInput.equals("Yes"))
        {
            System.out.println("Enter the birthday in DDMMYYYY format: ");
            this.birthday = myObj.nextLine();
        } 
        TakeLink(myObj);
        this.id = "I01" + Integer.toString(IDindex);
        System.out.println("Node created with unique ID: " + this.id);
        IDindex++;
    }

    void TakeLink(Scanner myObj)
    {
        String myInput = "Y";
        while(myInput.equals("Y"))
        {
            System.out.println("Is " + this.name + " a member of any organisation or Group?(Y/N)");
            myInput = myObj.nextLine();
            if(myInput.equals("Y"))
            {
                System.out.println("Enter the name of the user: ");
                //(this.LinkSet).Link = myObj.nextLine();
                this.isMember.add(myObj.nextLine());
            }

            else break;
        }

        myInput = "Y";
        while(myInput.equals("Y"))
        {
            System.out.println("Is " + this.name + " a customer of any business?(Y/N)");
            myInput = myObj.nextLine();
            if(myInput.equals("Y"))
            {
                System.out.println("Enter the name of the user: ");
                //(this.LinkSet).Link = myObj.nextLine();
                this.isCustomer.add(myObj.nextLine());
            }

            else break;
        }

        myInput = "Y";
        while(myInput.equals("Y"))
        {
            System.out.println("Is " + this.name + " an owner of any business?(Y/N)");
            myInput = myObj.nextLine();
            if(myInput.equals("Y"))
            {
                System.out.println("Enter the name of the user: ");
                //(this.LinkSet).Link = myObj.nextLine();
                this.isOwner.add(myObj.nextLine());
            }

            else break;
        }
    }

    void printNode()
    {
        super.printNode();
        if(!birthday.equals("Null")) System.out.println("Birthday: " + birthday);
    }

}

class groups extends node
{
    static int IDindexG = 0;
    ArrayList<String> Members = new ArrayList<>();

    public groups(Scanner myObj)
    {
        super(myObj);
        type = "group";

        TakeLinkG(myObj);
        this.id = "G01" + Integer.toString(IDindexG);
        System.out.println("Node created with unique ID: " + this.id);
        IDindexG++;
    }

    void TakeLinkG(Scanner myObj)
    {
        String myInput = "Y";
        while(myInput.equals("Y"))
        {
            System.out.println("Does " + this.name + " have any members?(Y/N)");
            myInput = myObj.nextLine();
            if(myInput.equals("Y"))
            {
                System.out.println("Enter the name of the user: ");
                //(this.LinkSet).Link = myObj.nextLine();
                this.Members.add(myObj.nextLine());
            }

            else break;
        }
    }

}

class businesses extends node
{   
    static int IDindexB = 0;
    int x;
    int y;
    ArrayList<String> Owners = new ArrayList<>();
    ArrayList<String> Customers = new ArrayList<>();
    ArrayList<String> isAMember = new ArrayList<>();

    public businesses(Scanner myObj)
    {
        super(myObj);
        type = "business";
        System.out.println("Enter the x-coordinate of location: ");
        x = myObj.nextInt();
        System.out.println("Enter the y-coordinate of location: ");
        y = myObj.nextInt();
        myObj.nextLine();
        TakeLink(myObj);
        this.id = "B01" + Integer.toString(IDindexB);
        System.out.println("Node created with unique ID: " + this.id);
        IDindexB++;
    }

    void TakeLink(Scanner myObj)
    {
        String myInput = "Y";
        while(myInput.equals("Y"))
        {
            System.out.println("Is " + this.name + " a member of any Group?(Y/N)");
            myInput = myObj.nextLine();
            if(myInput.equals("Y"))
            {
                System.out.println("Enter the name of the user: ");
                //(this.LinkSet).Link = myObj.nextLine();
                this.isAMember.add(myObj.nextLine());
            }

            else break;
        }

        myInput = "Y";
        while(myInput.equals("Y"))
        {
            System.out.println("Does " + this.name + " have a customer?(Y/N)");
            myInput = myObj.nextLine();
            if(myInput.equals("Y"))
            {
                System.out.println("Enter the name of the user: ");
                //(this.LinkSet).Link = myObj.nextLine();
                this.Customers.add(myObj.nextLine());
            }

            else break;
        }

        myInput = "Y";
        while(myInput.equals("Y"))
        {
            System.out.println("Does " + this.name + " have an owner?(Y/N)");
            myInput = myObj.nextLine();
            if(myInput.equals("Y"))
            {
                System.out.println("Enter the name of the user: ");
                //(this.LinkSet).Link = myObj.nextLine();
                this.Owners.add(myObj.nextLine());
            }

            else break;
        }
    }

    void printNode()
    {
        super.printNode();
        System.out.println("Coordinates of the location of the Business are: (" + x + ", " + y + ")");
    }

} 

class organisations extends node
{
    static int IDindexO = 0;
    int x;
    int y;
    ArrayList<String> Memberss = new ArrayList<>();

    public organisations(Scanner myObj)
    {
        super(myObj);
        type = "organisation";
        System.out.println("Enter the x-coordinate of location: ");
        x = myObj.nextInt();
        System.out.println("Enter the y-coordinate of location: ");
        y = myObj.nextInt();
        myObj.nextLine();
        TakeLinkO(myObj);
        this.id = "G01" + Integer.toString(IDindexO);
        System.out.println("Node created with unique ID: " + this.id);
        IDindexO++;
    }

    void TakeLinkO(Scanner myObj)
    {
        String myInput = "Y";
        while(myInput.equals("Y"))
        {
            System.out.println("Does " + this.name + " have any members?(Y/N)");
            myInput = myObj.nextLine();
            if(myInput.equals("Y"))
            {
                System.out.println("Enter the name of the user: ");
                //(this.LinkSet).Link = myObj.nextLine();
                this.Memberss.add(myObj.nextLine());
            }

            else break;
        }
    }

    void printNode()
    {
        super.printNode();
        System.out.println("Coordinates of the location of the Organisation are: (" + x + ", " + y + ")");
    }
} 