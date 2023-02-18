//
//  DestanetionFilter.swift
//  iosApp
//
//  Created by Данила Еремин on 18.02.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

enum DestanetionFilter: Hashable{
    case nutrition
}

class NavigatorFilter: ObservableObject{
    @Published  var path: [DestanetionFilter] = []
    
    func popBackStack(){
        path.removeLast()
    }
}

