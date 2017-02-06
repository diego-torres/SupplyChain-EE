export const MENU = [
  {
    path: '',
    children: [
      {
        path: 'home',
        data: {
          menu: {
            title: 'Tablero',
            icon: 'ion-android-home',
            selected: true,
            expanded: false,
            order: 0
          }
        }
      },
      {
        path: 'catalogs',
        data: {
          menu: {
            title: 'Catálogos',
            icon: 'ion-edit',
            selected: false,
            expanded: false,
            order: 100,
          }
        },
        children: [
          {
            path: 'parts',
            data: {
              menu: {
                title: 'Partes',
              }
            }
          },
          {
            path: 'clients',
            data: {
              menu: {
                title: 'Clientes',
              }
            }
          }
        ]
      },
      {
        path: 'packing-list',
        data: {
          menu: {
            title: 'Listas Empaque',
            icon: 'ion-ios-box-outline',
            selected: false,
            expanded: false,
            order: 250,
          }
        },
        children: [
          {
            path: 'lista',
            data: {
              menu: {
                title: 'Lista',
              }
            }
          },
          {
            path: 'create',
            data: {
              menu: {
                title: 'Nueva',
              }
            }
          }
        ]
      }
    ]
  }
];
