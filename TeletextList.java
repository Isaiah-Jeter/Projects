import java.awt.Graphics;

/**
 * Implements the list of messages for teletext
 */
public class TeletextList
{
  private ListNode2 heading, topNode;

  /**
   * Creates a circular list of headlines.
   * First creates a circular list with one node, "Today's headlines:".
   * Saves a reference to that node in heading.
   * Adds a node holding an empty string before heading
   * and another node holding an empty string after heading.
   * Appends all the strings from headlines to the list, after
   * the blank line that follows heading,
   * preserving their order.  Sets topNode equal to heading.
   */
  public TeletextList(String[] headlines)
  {
	  heading = new ListNode2 ("Today's headlines", new ListNode2 (""), new ListNode2 (""));
	  topNode = heading;
	  topNode.getNext().setPrevious(topNode);
	  topNode.getPrevious().setNext(topNode);
	  topNode.getNext().setNext(topNode.getPrevious());
	  topNode.getPrevious().setPrevious(topNode.getNext());
	  topNode = heading.getNext();
	  if (headlines.length == 1) {
	  	topNode.setNext(new ListNode2 (headlines[0], heading.getNext(), heading.getPrevious()));
	  	topNode = topNode.getNext();
	  	topNode.getNext().setPrevious(topNode);
	  }
	  else {
  	  	for (int a = 0; a<headlines.length - 1; a++) {
  	  		topNode.setNext (new ListNode2 (headlines[a], topNode, new ListNode2 (headlines[a+1])));
  	  		topNode = topNode.getNext();
  	  		topNode.getNext().setPrevious(topNode);
  	  		if (a == headlines.length-2) {
  	  			topNode.getNext().setNext(heading.getPrevious());
  	  			heading.getPrevious().setPrevious(topNode.getNext());
  	  		}
  	  	}
  	  }
  	 topNode = heading;
  }

  /**
   * Inserts a node with msg into the headlines list after the blank
   * line that follows heading.
   */
  public void insert(String msg)
  {
	  heading.getNext().setNext(new ListNode2 (msg, heading.getNext(),heading.getNext().getNext()));
	  heading.getNext().getNext().getNext().setPrevious(heading.getNext().getNext());
  }

  /**
   * Deletes the node that follows topNode from the headlines list,
   * unless that node happens to be heading or the node before or after
   * heading that holds a blank line.
   */
  public void delete()
  {
  	if (!topNode.equals(heading) && !topNode.getNext().equals(heading) && !topNode.getNext().getNext().equals(heading)) {
  		topNode.setNext(topNode.getNext().getNext());
  		topNode.getNext().setPrevious(topNode);
  	}
  }

  /**
   * Scrolls up the headlines list, advancing topNode to the next node.
   */
  public void scrollUp()
  {
	  topNode = topNode.getNext();
  }

  /**
   * Adds a new node with msg to the headlines list before a given node.
   * Returns a reference to the added node.
   */
  private ListNode2 addBefore(ListNode2 node, String msg)
  {
	 if (!node.equals(heading) && !node.equals(heading.getNext())) {
	  node.getPrevious().setNext(new ListNode2 (msg, node.getPrevious(), node));
	  node.setPrevious(node.getPrevious().getNext());
	  return (node.getPrevious());
	}
	else {
		return heading;
	}
  }

  /**
   * Adds a new node with msg to the headlines list after a given node.
   * Returns a reference to the added node.
   */
  private ListNode2 addAfter(ListNode2 node, String msg)
  {
  	if (!node.equals(heading) && !node.equals(heading.getPrevious())) {
  	node.getNext().setPrevious(new ListNode2 (msg, node, node.getNext()));
  	node.setNext(node.getNext().getPrevious());
  	return (node.getNext());
  	}
  	else {
  		return heading;
  	}
  }

  /**
   * Removes a given node from the list.
   */
  private void remove(ListNode2 node)
  {
	  if (!node.equals(heading) && !node.equals(heading.getPrevious()) && !node.equals(heading.getNext())) {
	  node.getPrevious().setNext(node.getNext());
	  node.getNext().setPrevious(node.getPrevious());
	}
  }

  /**
   * Draws nLines headlines in g, starting with topNode at x, y
   * and incrementing y by lineHeight after each headline.
   */
  public void draw(Graphics g, int x, int y, int lineHeight, int nLines)
  {
    ListNode2 node = topNode;
    for (int k = 1; k <= nLines; k++)
    {
      g.drawString((String)node.getValue(), x, y);
      y += lineHeight;
      node = node.getNext();
    }
  }
}
