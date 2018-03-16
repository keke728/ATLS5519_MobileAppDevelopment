//
//  MemoTableViewController.swift
//  
//
//  Created by Keke Wu on 3/15/18.
//

import UIKit
import RealmSwift

class MemoTableViewController: UITableViewController {
    
    var realm: Realm! //Realm DB Instance
    var Memolist: Results<Memo>{
        get {
            return realm.objects(Memo.self) //Returns all Grocery Objects from Realm
        }
    }
    
    @IBAction func addMemo(_ sender: UIBarButtonItem) {
        let addalert = UIAlertController(title: "New Memo", message: "Add a new memo to your list", preferredStyle: .alert)
        //add textfield to the alert
        addalert.addTextField(configurationHandler: {(UITextField) in
        })
        let cancelAction = UIAlertAction(title: "Cancel", style: .cancel, handler: nil)
        addalert.addAction(cancelAction)
        let addNewAction = UIAlertAction(title: "Add", style: .default, handler: {(UIAlertAction)in
            // adds new item
            let newitem = addalert.textFields![0] //gets textfield
            let newMemo = Memo() //new Memo instance
            newMemo.name = newitem.text! //set name with textfiled text
            newMemo.done = false
            
            do {
                try self.realm.write({
                    self.realm.add(newMemo) //Add to Realm DB
                    self.tableView.insertRows(at: [IndexPath.init(row: self.Memolist.count - 1, section: 0)], with: .automatic) //Inserts new row at the end of the table
                })
            } catch let error {
                print(error.localizedDescription)
            }
        })
        addalert.addAction(addNewAction)
        present(addalert, animated: true, completion: nil)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        
        //Initialize the Realm Variable
        do {
            realm = try Realm()
        } catch let error {
            print(error.localizedDescription)
        }
        print(Realm.Configuration.defaultConfiguration.fileURL!)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return Memolist.count
    }

    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath)
        let item = Memolist[indexPath.row]
        cell.textLabel!.text = item.name
        cell.accessoryType = item.done ? .checkmark : .none //Set checkmark if done
        return cell
    }
   

    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let item = Memolist[indexPath.row]
        try! self.realm.write {
            item.done = !item.done
        }
        tableView.reloadRows(at: [indexPath], with: .automatic)
    }

    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        return true
    }

    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete{
            let item = Memolist[indexPath.row]
            try! self.realm.write {
                 self.realm.delete(item)  //Delete from Realm D
        }
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
        }
    }
}
