//
//  GoalsViewController.swift
//  Happy Ivy
//
//  Created by Keke Wu on 3/3/18.
//  Copyright © 2018 Keke Wu. All rights reserved.
//

import UIKit

var iconSets = [String]()

class GoalsViewController: UIViewController, UICollectionViewDelegate, UICollectionViewDataSource, UICollectionViewDelegateFlowLayout{
        
    //Set cell positions for collectionview
    let sectionInsets = UIEdgeInsets(top:150.0, left: 20.0, bottom: 20.0, right: 20.0)
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int) -> UIEdgeInsets {
        return sectionInsets
    }
    
    
    @IBOutlet weak var CollectionView: UICollectionView!
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return iconSets.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        //Set different icons for collectionview cells
        let cell = CollectionView.dequeueReusableCell(withReuseIdentifier: "CustomCell",for: indexPath) as! CustomCollectionViewCell
        cell.cell_1.image = UIImage(named:iconSets[indexPath.row])
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {

    }
    
    //Passing icon to AddToListViewController
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "AddGoals" {
            let indexPath = CollectionView?.indexPath(for: sender as! CustomCollectionViewCell)
            let detailVC = segue.destination as! AddToListViewController
            detailVC.imageName = iconSets[(indexPath?.row)!]
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        //Set Background Image
        let backgroundImage = UIImageView(frame: UIScreen.main.bounds)
        backgroundImage.image = UIImage(named: "Goals_BG")
        backgroundImage.contentMode = UIViewContentMode.scaleAspectFill
        self.view.insertSubview(backgroundImage, at: 0)
        // Do any additional setup after loading the view.
        CollectionView.delegate = self
        CollectionView.dataSource = self
        
        //Loop iconSets images
        for i in 1...20{
            iconSets.append("icon" + String(i))
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    



}
