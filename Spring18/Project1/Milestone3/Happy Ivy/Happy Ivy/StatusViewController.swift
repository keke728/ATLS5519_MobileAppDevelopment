//
//  StatusViewController.swift
//  Happy Ivy
//
//  Created by Keke Wu on 3/3/18.
//  Copyright © 2018 Keke Wu. All rights reserved.
//

import UIKit

class StatusViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        loadData()
        //Set Background Image
        let backgroundImage = UIImageView(frame: UIScreen.main.bounds)
        backgroundImage.image = UIImage(named: "Status_BG")
        backgroundImage.contentMode = UIViewContentMode.scaleAspectFill
        self.view.insertSubview(backgroundImage, at: 0)

        // Do any additional setup after loading the view.
    
    }

    //Separate Function to Refresh ViewDidLoad
    func loadData(){
        //Tab Bar Icons
        let tabBar: UITabBar = tabBarController!.tabBar
        let tabBarItem1: UITabBarItem = tabBar.items![0]
        let tabBarItem2: UITabBarItem = tabBar.items![1]
        let tabBarItem3: UITabBarItem = tabBar.items![2]
        let tabBarItem4: UITabBarItem = tabBar.items![3]
        tabBarItem1.image = UIImage(named:"tab3_1")
        tabBarItem2.image = UIImage(named:"tab3_2")
        tabBarItem3.image = UIImage(named:"tab3_3")
        tabBarItem4.image = UIImage(named:"tab3_4")
    }
    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    //Call loadData() again
    override func viewWillAppear(_ animated: Bool) {
        loadData()
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
