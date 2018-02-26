//
//  ChracterViewController.swift
//  Dotadex
//
//  Created by Keke Wu on 2/25/18.
//  Copyright © 2018 Keke Wu. All rights reserved.
//

import UIKit

//UIImageView Extension
//Reference https://stackoverflow.com/questions/24231680/loading-downloading-image-from-url-on-swift
extension UIImageView {
    func downloadedFrom(url: URL, contentMode mode: UIViewContentMode = .scaleAspectFit) {
        contentMode = mode
        URLSession.shared.dataTask(with: url) { data, response, error in
            guard
                let httpURLResponse = response as? HTTPURLResponse, httpURLResponse.statusCode == 200,
                let mimeType = response?.mimeType, mimeType.hasPrefix("image"),
                let data = data, error == nil,
                let image = UIImage(data: data)
                else { return }
            DispatchQueue.main.async() {
                self.image = image
            }
            }.resume()
    }
    func downloadedFrom(link: String, contentMode mode: UIViewContentMode = .scaleAspectFit) {
        guard let url = URL(string: link) else { return }
        downloadedFrom(url: url, contentMode: mode)
    }
}



class ChracterViewController: UIViewController {

    @IBOutlet weak var imgView: UIImageView!
    @IBOutlet weak var nameLabel: UILabel!
    
    var characters:Chracter?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        nameLabel.text = characters?.localized_name
        let urlString = "https://api.opendota.com" + (characters?.img)!
        let url = URL(string: urlString)
        imgView.downloadedFrom(url: url!)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()

    }
    
}
